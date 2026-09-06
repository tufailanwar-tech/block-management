package com.railway.blockoptimizer.service;

import com.railway.blockoptimizer.domain.entity.Corridor;
import com.railway.blockoptimizer.domain.entity.Train;
import com.railway.blockoptimizer.domain.entity.TrainSchedule;
import com.railway.blockoptimizer.domain.enums.SourceSystem;
import com.railway.blockoptimizer.dto.TrainDTO;
import com.railway.blockoptimizer.dto.TrainScheduleDTO;
import com.railway.blockoptimizer.exception.ResourceNotFoundException;
import com.railway.blockoptimizer.repository.CorridorRepository;
import com.railway.blockoptimizer.repository.TrainRepository;
import com.railway.blockoptimizer.repository.TrainScheduleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TrainService {

    private final TrainRepository trainRepository;
    private final TrainScheduleRepository trainScheduleRepository;
    private final CorridorRepository corridorRepository;

    public TrainService(TrainRepository trainRepository, TrainScheduleRepository trainScheduleRepository, CorridorRepository corridorRepository) {
        this.trainRepository = trainRepository;
        this.trainScheduleRepository = trainScheduleRepository;
        this.corridorRepository = corridorRepository;
    }

    public List<TrainDTO> getAllTrains() {
        return trainRepository.findAll().stream()
                .map(this::mapTrainToDTO)
                .collect(Collectors.toList());
    }

    public TrainDTO createTrain(TrainDTO dto) {
        Train train = Train.builder()
                .trainNumber(dto.getTrainNumber().toUpperCase().trim())
                .trainName(dto.getTrainName().trim())
                .trainType(dto.getTrainType())
                .priorityLevel(dto.getPriorityLevel())
                .originStation(dto.getOriginStation().toUpperCase().trim())
                .destinationStation(dto.getDestinationStation().toUpperCase().trim())
                .sourceSystem(dto.getSourceSystem() != null ? dto.getSourceSystem() : SourceSystem.MANUAL)
                .build();

        Train saved = trainRepository.save(train);
        return mapTrainToDTO(saved);
    }

    public List<TrainScheduleDTO> getAllSchedules() {
        return trainScheduleRepository.findAll().stream()
                .map(this::mapScheduleToDTO)
                .collect(Collectors.toList());
    }

    public TrainScheduleDTO createSchedule(TrainScheduleDTO dto) {
        Train train = trainRepository.findById(dto.getTrainId())
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with ID: " + dto.getTrainId()));

        Corridor corridor = corridorRepository.findById(dto.getCorridorId())
                .orElseThrow(() -> new ResourceNotFoundException("Corridor not found with ID: " + dto.getCorridorId()));

        TrainSchedule schedule = TrainSchedule.builder()
                .train(train)
                .corridor(corridor)
                .arrivalTime(dto.getArrivalTime())
                .departureTime(dto.getDepartureTime())
                .startKm(dto.getStartKm())
                .endKm(dto.getEndKm())
                .dayOfWeek(dto.getDayOfWeek().toUpperCase().trim())
                .frequencyPattern(dto.getFrequencyPattern())
                .build();

        TrainSchedule saved = trainScheduleRepository.save(schedule);
        return mapScheduleToDTO(saved);
    }

    public TrainDTO mapTrainToDTO(Train train) {
        return TrainDTO.builder()
                .id(train.getId())
                .trainNumber(train.getTrainNumber())
                .trainName(train.getTrainName())
                .trainType(train.getTrainType())
                .priorityLevel(train.getPriorityLevel())
                .originStation(train.getOriginStation())
                .destinationStation(train.getDestinationStation())
                .sourceSystem(train.getSourceSystem())
                .build();
    }

    public TrainScheduleDTO mapScheduleToDTO(TrainSchedule schedule) {
        return TrainScheduleDTO.builder()
                .id(schedule.getId())
                .trainId(schedule.getTrain() != null ? schedule.getTrain().getId() : null)
                .trainNumber(schedule.getTrain() != null ? schedule.getTrain().getTrainNumber() : null)
                .trainName(schedule.getTrain() != null ? schedule.getTrain().getTrainName() : null)
                .corridorId(schedule.getCorridor() != null ? schedule.getCorridor().getId() : null)
                .corridorCode(schedule.getCorridor() != null ? schedule.getCorridor().getCode() : null)
                .arrivalTime(schedule.getArrivalTime())
                .departureTime(schedule.getDepartureTime())
                .startKm(schedule.getStartKm())
                .endKm(schedule.getEndKm())
                .dayOfWeek(schedule.getDayOfWeek())
                .frequencyPattern(schedule.getFrequencyPattern())
                .build();
    }
}
