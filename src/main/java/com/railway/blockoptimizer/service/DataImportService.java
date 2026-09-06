package com.railway.blockoptimizer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.railway.blockoptimizer.domain.entity.*;
import com.railway.blockoptimizer.domain.enums.*;
import com.railway.blockoptimizer.dto.ImportResultDTO;
import com.railway.blockoptimizer.repository.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Service
public class DataImportService {

    private static final Logger log = LoggerFactory.getLogger(DataImportService.class);

    private final CorridorRepository corridorRepository;
    private final AssetRepository assetRepository;
    private final TrainRepository trainRepository;
    private final TrainScheduleRepository trainScheduleRepository;
    private final MaintenanceTaskRepository maintenanceTaskRepository;
    private final DepartmentRepository departmentRepository;
    private final BlockRequestRepository blockRequestRepository;
    private final ObjectMapper objectMapper;

    public DataImportService(CorridorRepository corridorRepository, AssetRepository assetRepository, TrainRepository trainRepository, TrainScheduleRepository trainScheduleRepository, MaintenanceTaskRepository maintenanceTaskRepository, DepartmentRepository departmentRepository, BlockRequestRepository blockRequestRepository, ObjectMapper objectMapper) {
        this.corridorRepository = corridorRepository;
        this.assetRepository = assetRepository;
        this.trainRepository = trainRepository;
        this.trainScheduleRepository = trainScheduleRepository;
        this.maintenanceTaskRepository = maintenanceTaskRepository;
        this.departmentRepository = departmentRepository;
        this.blockRequestRepository = blockRequestRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public ImportResultDTO importCSV(MultipartFile file, String entityType) {
        ImportResultDTO result = ImportResultDTO.builder()
                .entityType(entityType.toUpperCase())
                .build();

        try (Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
             CSVParser parser = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).setIgnoreHeaderCase(true).setTrim(true).build().parse(reader)) {

            List<CSVRecord> records = parser.getRecords();
            result.setTotalRecords(records.size());

            int rowIdx = 1;
            for (CSVRecord record : records) {
                rowIdx++;
                try {
                    switch (entityType.toLowerCase()) {
                        case "corridors" -> processCorridorRow(record, result);
                        case "assets" -> processAssetRow(record, result);
                        case "trains" -> processTrainRow(record, result);
                        case "schedules" -> processScheduleRow(record, result);
                        case "tasks" -> processTaskRow(record, result);
                        case "block-requests" -> processBlockRequestRow(record, result);
                        default -> throw new IllegalArgumentException("Unsupported entity type: " + entityType);
                    }
                } catch (Exception e) {
                    result.setFailureCount(result.getFailureCount() + 1);
                    result.getErrors().add("Row " + rowIdx + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Failed to parse CSV import file", e);
            result.getErrors().add("CSV Parsing Error: " + e.getMessage());
        }

        return result;
    }

    @Transactional
    public ImportResultDTO importJSON(String jsonContent, String entityType) {
        ImportResultDTO result = ImportResultDTO.builder()
                .entityType(entityType.toUpperCase())
                .build();

        try {
            List<Map<String, Object>> records = objectMapper.readValue(jsonContent, new TypeReference<>() {});
            result.setTotalRecords(records.size());

            int index = 0;
            for (Map<String, Object> record : records) {
                index++;
                try {
                    switch (entityType.toLowerCase()) {
                        case "corridors" -> processCorridorMap(record, result);
                        case "trains" -> processTrainMap(record, result);
                        case "tasks" -> processTaskMap(record, result);
                        default -> throw new IllegalArgumentException("Unsupported entity type: " + entityType);
                    }
                } catch (Exception e) {
                    result.setFailureCount(result.getFailureCount() + 1);
                    result.getErrors().add("Item " + index + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Failed to parse JSON import content", e);
            result.getErrors().add("JSON Parsing Error: " + e.getMessage());
        }

        return result;
    }

    private void processCorridorRow(CSVRecord record, ImportResultDTO result) {
        String code = record.get("code").toUpperCase().trim();
        String name = record.get("name").trim();
        String source = record.get("source_station").toUpperCase().trim();
        String dest = record.get("destination_station").toUpperCase().trim();
        Double length = Double.parseDouble(record.get("length_km"));
        Integer maxSpeed = Integer.parseInt(record.get("max_speed_kmh"));

        Corridor corridor = corridorRepository.findByCode(code).orElse(Corridor.builder().code(code).build());
        corridor.setName(name);
        corridor.setSourceStation(source);
        corridor.setDestinationStation(dest);
        corridor.setLengthKm(length);
        corridor.setMaxSpeedKmh(maxSpeed);
        corridor.setNumberOfTracks(TrackType.valueOf(record.isSet("number_of_tracks") ? record.get("number_of_tracks").toUpperCase() : "DOUBLE"));
        corridor.setStatus(CorridorStatus.valueOf(record.isSet("status") ? record.get("status").toUpperCase() : "ACTIVE"));

        corridorRepository.save(corridor);
        result.setSuccessCount(result.getSuccessCount() + 1);
        result.getImportedKeys().add(code);
    }

    private void processAssetRow(CSVRecord record, ImportResultDTO result) {
        String assetCode = record.get("asset_code").toUpperCase().trim();
        String corridorCode = record.get("corridor_code").toUpperCase().trim();

        Corridor corridor = corridorRepository.findByCode(corridorCode)
                .orElseThrow(() -> new IllegalArgumentException("Corridor not found: " + corridorCode));

        Asset asset = assetRepository.findByAssetCode(assetCode).orElse(Asset.builder().assetCode(assetCode).build());
        asset.setCorridor(corridor);
        asset.setAssetType(AssetType.valueOf(record.get("asset_type").toUpperCase().trim()));
        asset.setLocationKmStart(Double.parseDouble(record.get("location_km_start")));
        asset.setLocationKmEnd(Double.parseDouble(record.get("location_km_end")));
        asset.setStatus(AssetStatus.valueOf(record.isSet("status") ? record.get("status").toUpperCase() : "OPERATIONAL"));

        assetRepository.save(asset);
        result.setSuccessCount(result.getSuccessCount() + 1);
        result.getImportedKeys().add(assetCode);
    }

    private void processTrainRow(CSVRecord record, ImportResultDTO result) {
        String trainNumber = record.get("train_number").toUpperCase().trim();
        String trainName = record.get("train_name").trim();

        Train train = trainRepository.findByTrainNumber(trainNumber).orElse(Train.builder().trainNumber(trainNumber).build());
        train.setTrainName(trainName);
        train.setTrainType(TrainType.valueOf(record.get("train_type").toUpperCase().trim()));
        train.setPriorityLevel(Integer.parseInt(record.get("priority_level")));
        train.setOriginStation(record.get("origin_station").toUpperCase().trim());
        train.setDestinationStation(record.get("destination_station").toUpperCase().trim());
        train.setSourceSystem(SourceSystem.valueOf(record.isSet("source_system") ? record.get("source_system").toUpperCase() : "MANUAL"));

        trainRepository.save(train);
        result.setSuccessCount(result.getSuccessCount() + 1);
        result.getImportedKeys().add(trainNumber);
    }

    private void processScheduleRow(CSVRecord record, ImportResultDTO result) {
        String trainNumber = record.get("train_number").toUpperCase().trim();
        String corridorCode = record.get("corridor_code").toUpperCase().trim();

        Train train = trainRepository.findByTrainNumber(trainNumber)
                .orElseThrow(() -> new IllegalArgumentException("Train not found: " + trainNumber));
        Corridor corridor = corridorRepository.findByCode(corridorCode)
                .orElseThrow(() -> new IllegalArgumentException("Corridor not found: " + corridorCode));

        TrainSchedule schedule = TrainSchedule.builder()
                .train(train)
                .corridor(corridor)
                .arrivalTime(OffsetDateTime.parse(record.get("arrival_time")))
                .departureTime(OffsetDateTime.parse(record.get("departure_time")))
                .startKm(Double.parseDouble(record.get("start_km")))
                .endKm(Double.parseDouble(record.get("end_km")))
                .dayOfWeek(record.get("day_of_week").toUpperCase().trim())
                .frequencyPattern(record.isSet("frequency_pattern") ? record.get("frequency_pattern") : "DAILY")
                .build();

        trainScheduleRepository.save(schedule);
        result.setSuccessCount(result.getSuccessCount() + 1);
        result.getImportedKeys().add(trainNumber + "-" + corridorCode);
    }

    private void processTaskRow(CSVRecord record, ImportResultDTO result) {
        String deptCode = record.get("department_code").toUpperCase().trim();
        Department dept = departmentRepository.findByCode(deptCode)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + deptCode));

        MaintenanceTask task = MaintenanceTask.builder()
                .department(dept)
                .taskType(record.get("task_type").trim())
                .title(record.get("title").trim())
                .description(record.isSet("description") ? record.get("description") : "")
                .priority(TaskPriority.valueOf(record.get("priority").toUpperCase().trim()))
                .estimatedDurationMinutes(Integer.parseInt(record.get("estimated_duration_minutes")))
                .requiredBlockType(BlockType.valueOf(record.get("required_block_type").toUpperCase().trim()))
                .periodicFrequency(PeriodicFrequency.valueOf(record.get("periodic_frequency").toUpperCase().trim()))
                .status(TaskStatus.SUBMITTED)
                .sourceSystem(SourceSystem.valueOf(record.isSet("source_system") ? record.get("source_system").toUpperCase() : "MANUAL"))
                .build();

        maintenanceTaskRepository.save(task);
        result.setSuccessCount(result.getSuccessCount() + 1);
        result.getImportedKeys().add(task.getTitle());
    }

    private void processBlockRequestRow(CSVRecord record, ImportResultDTO result) {
        String deptCode = record.get("department_code").toUpperCase().trim();
        String corridorCode = record.get("corridor_code").toUpperCase().trim();

        Department dept = departmentRepository.findByCode(deptCode)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + deptCode));
        Corridor corridor = corridorRepository.findByCode(corridorCode)
                .orElseThrow(() -> new IllegalArgumentException("Corridor not found: " + corridorCode));

        OffsetDateTime start = OffsetDateTime.parse(record.get("start_time"));
        OffsetDateTime end = OffsetDateTime.parse(record.get("end_time"));

        if (end.isBefore(start)) {
            throw new IllegalArgumentException("end_time must be after start_time");
        }

        BlockRequest req = BlockRequest.builder()
                .requestNumber("BR-IMP-" + System.currentTimeMillis() % 10000)
                .department(dept)
                .corridor(corridor)
                .blockType(BlockType.valueOf(record.get("block_type").toUpperCase().trim()))
                .startTime(start)
                .endTime(end)
                .durationMinutes((int) java.time.Duration.between(start, end).toMinutes())
                .startKm(Double.parseDouble(record.get("start_km")))
                .endKm(Double.parseDouble(record.get("end_km")))
                .status(BlockRequestStatus.PENDING)
                .remarks(record.isSet("remarks") ? record.get("remarks") : "Imported via CSV")
                .build();

        blockRequestRepository.save(req);
        result.setSuccessCount(result.getSuccessCount() + 1);
        result.getImportedKeys().add(req.getRequestNumber());
    }

    private void processCorridorMap(Map<String, Object> map, ImportResultDTO result) {
        String code = ((String) map.get("code")).toUpperCase().trim();
        Corridor corridor = corridorRepository.findByCode(code).orElse(Corridor.builder().code(code).build());
        corridor.setName((String) map.get("name"));
        corridor.setSourceStation(((String) map.get("sourceStation")).toUpperCase());
        corridor.setDestinationStation(((String) map.get("destinationStation")).toUpperCase());
        corridor.setLengthKm(Double.valueOf(map.get("lengthKm").toString()));
        corridor.setMaxSpeedKmh(Integer.valueOf(map.get("maxSpeedKmh").toString()));
        corridor.setNumberOfTracks(TrackType.valueOf(map.getOrDefault("numberOfTracks", "DOUBLE").toString().toUpperCase()));
        corridor.setStatus(CorridorStatus.valueOf(map.getOrDefault("status", "ACTIVE").toString().toUpperCase()));

        corridorRepository.save(corridor);
        result.setSuccessCount(result.getSuccessCount() + 1);
        result.getImportedKeys().add(code);
    }

    private void processTrainMap(Map<String, Object> map, ImportResultDTO result) {
        String trainNumber = ((String) map.get("trainNumber")).toUpperCase().trim();
        Train train = trainRepository.findByTrainNumber(trainNumber).orElse(Train.builder().trainNumber(trainNumber).build());
        train.setTrainName((String) map.get("trainName"));
        train.setTrainType(TrainType.valueOf(map.get("trainType").toString().toUpperCase()));
        train.setPriorityLevel(Integer.valueOf(map.get("priorityLevel").toString()));
        train.setOriginStation(((String) map.get("originStation")).toUpperCase());
        train.setDestinationStation(((String) map.get("destinationStation")).toUpperCase());
        train.setSourceSystem(SourceSystem.valueOf(map.getOrDefault("sourceSystem", "MANUAL").toString().toUpperCase()));

        trainRepository.save(train);
        result.setSuccessCount(result.getSuccessCount() + 1);
        result.getImportedKeys().add(trainNumber);
    }

    private void processTaskMap(Map<String, Object> map, ImportResultDTO result) {
        String deptCode = ((String) map.get("departmentCode")).toUpperCase().trim();
        Department dept = departmentRepository.findByCode(deptCode)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + deptCode));

        MaintenanceTask task = MaintenanceTask.builder()
                .department(dept)
                .taskType((String) map.get("taskType"))
                .title((String) map.get("title"))
                .description((String) map.getOrDefault("description", ""))
                .priority(TaskPriority.valueOf(map.get("priority").toString().toUpperCase()))
                .estimatedDurationMinutes(Integer.valueOf(map.get("estimatedDurationMinutes").toString()))
                .requiredBlockType(BlockType.valueOf(map.get("requiredBlockType").toString().toUpperCase()))
                .periodicFrequency(PeriodicFrequency.valueOf(map.get("periodicFrequency").toString().toUpperCase()))
                .status(TaskStatus.SUBMITTED)
                .sourceSystem(SourceSystem.valueOf(map.getOrDefault("sourceSystem", "MANUAL").toString().toUpperCase()))
                .build();

        maintenanceTaskRepository.save(task);
        result.setSuccessCount(result.getSuccessCount() + 1);
        result.getImportedKeys().add(task.getTitle());
    }
}
