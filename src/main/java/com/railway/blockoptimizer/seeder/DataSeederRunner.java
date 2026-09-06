package com.railway.blockoptimizer.seeder;

import com.railway.blockoptimizer.domain.entity.*;
import com.railway.blockoptimizer.domain.enums.*;
import com.railway.blockoptimizer.repository.*;
import com.railway.blockoptimizer.service.ConflictDetectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Component
public class DataSeederRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeederRunner.class);

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final CorridorRepository corridorRepository;
    private final AssetRepository assetRepository;
    private final MaintenanceTaskRepository maintenanceTaskRepository;
    private final TrainRepository trainRepository;
    private final TrainScheduleRepository trainScheduleRepository;
    private final BlockRequestRepository blockRequestRepository;
    private final BlockPlanRepository blockPlanRepository;
    private final ConflictDetectionService conflictDetectionService;

    public DataSeederRunner(DepartmentRepository departmentRepository, UserRepository userRepository, CorridorRepository corridorRepository, AssetRepository assetRepository, MaintenanceTaskRepository maintenanceTaskRepository, TrainRepository trainRepository, TrainScheduleRepository trainScheduleRepository, BlockRequestRepository blockRequestRepository, BlockPlanRepository blockPlanRepository, ConflictDetectionService conflictDetectionService) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.corridorRepository = corridorRepository;
        this.assetRepository = assetRepository;
        this.maintenanceTaskRepository = maintenanceTaskRepository;
        this.trainRepository = trainRepository;
        this.trainScheduleRepository = trainScheduleRepository;
        this.blockRequestRepository = blockRequestRepository;
        this.blockPlanRepository = blockPlanRepository;
        this.conflictDetectionService = conflictDetectionService;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (departmentRepository.count() > 0) {
            log.info("Database already contains data. Skipping synthetic data seeding.");
            return;
        }

        log.info("Seeding synthetic Indian Railway sample data for PS27 Part 1 Data Layer...");

        Department trd = departmentRepository.save(Department.builder().code("TRD").name("Traction Distribution (OHE)").description("Electrical overhead equipment maintenance").build());
        Department snt = departmentRepository.save(Department.builder().code("SNT").name("Signal & Telecommunication").description("Signaling systems and interlocking").build());
        Department engg = departmentRepository.save(Department.builder().code("ENGG").name("Engineering (Permanent Way)").description("Track, rail, switch and civil asset maintenance").build());
        Department oper = departmentRepository.save(Department.builder().code("OPER").name("Operations").description("Train dispatch and yard management").build());

        User admin = userRepository.save(User.builder().email("admin@railway.gov.in").fullName("System Administrator").role(Role.ADMIN).department(oper).build());
        User planner = userRepository.save(User.builder().email("planner@railway.gov.in").fullName("Chief Block Planner").role(Role.PLANNER).department(oper).build());
        User engUser = userRepository.save(User.builder().email("trd.officer@railway.gov.in").fullName("TRD Senior Engineer").role(Role.DEPARTMENT_USER).department(trd).build());
        User approver = userRepository.save(User.builder().email("approver@railway.gov.in").fullName("Divisional Railway Manager").role(Role.APPROVER).department(oper).build());

        Corridor corridor1 = corridorRepository.save(Corridor.builder()
                .code("NDLS-CNB")
                .name("New Delhi - Kanpur Central Main Line")
                .sourceStation("NDLS")
                .destinationStation("CNB")
                .lengthKm(440.0)
                .maxSpeedKmh(130)
                .numberOfTracks(TrackType.MULTIPLE)
                .status(CorridorStatus.ACTIVE)
                .build());

        Corridor corridor2 = corridorRepository.save(Corridor.builder()
                .code("BCT-ADI")
                .name("Mumbai Central - Ahmedabad Western Corridor")
                .sourceStation("BCT")
                .destinationStation("ADI")
                .lengthKm(492.0)
                .maxSpeedKmh(160)
                .numberOfTracks(TrackType.DOUBLE)
                .status(CorridorStatus.ACTIVE)
                .build());

        Asset oheAsset = assetRepository.save(Asset.builder()
                .corridor(corridor1)
                .assetCode("OHE-NDLS-120")
                .assetType(AssetType.OHE)
                .locationKmStart(120.0)
                .locationKmEnd(135.0)
                .status(AssetStatus.OPERATIONAL)
                .build());

        Asset trackAsset = assetRepository.save(Asset.builder()
                .corridor(corridor1)
                .assetCode("TRK-CNB-045")
                .assetType(AssetType.TRACK_SEGMENT)
                .locationKmStart(45.0)
                .locationKmEnd(50.0)
                .status(AssetStatus.OPERATIONAL)
                .build());

        Asset signalAsset = assetRepository.save(Asset.builder()
                .corridor(corridor2)
                .assetCode("SIG-ADI-210")
                .assetType(AssetType.SIGNAL)
                .locationKmStart(210.0)
                .locationKmEnd(212.0)
                .status(AssetStatus.OPERATIONAL)
                .build());

        MaintenanceTask task1 = maintenanceTaskRepository.save(MaintenanceTask.builder()
                .department(trd)
                .asset(oheAsset)
                .taskType("Overhead Catenary Periodic Inspection")
                .title("TRD OHE Inspection KM 120-135")
                .description("Routine tensioning and insulator washing on main line")
                .priority(TaskPriority.HIGH)
                .estimatedDurationMinutes(180)
                .requiredBlockType(BlockType.POWER_BLOCK)
                .periodicFrequency(PeriodicFrequency.WEEKLY)
                .status(TaskStatus.APPROVED)
                .sourceSystem(SourceSystem.SMMS)
                .build());

        MaintenanceTask task2 = maintenanceTaskRepository.save(MaintenanceTask.builder()
                .department(engg)
                .asset(trackAsset)
                .taskType("Ultrasonic Flaw Detection & Tamping")
                .title("Track USFD Testing KM 45-50")
                .description("Heavy rail flaw detection and automatic track tamping")
                .priority(TaskPriority.CRITICAL)
                .estimatedDurationMinutes(240)
                .requiredBlockType(BlockType.INTEGRATED_BLOCK)
                .periodicFrequency(PeriodicFrequency.MONTHLY)
                .status(TaskStatus.SUBMITTED)
                .sourceSystem(SourceSystem.MANUAL)
                .build());

        Train rajdhani = trainRepository.save(Train.builder()
                .trainNumber("12301")
                .trainName("Howrah Rajdhani Express")
                .trainType(TrainType.PASSENGER_EXPRESS)
                .priorityLevel(1)
                .originStation("HWH")
                .destinationStation("NDLS")
                .sourceSystem(SourceSystem.TMS)
                .build());

        Train shatabdi = trainRepository.save(Train.builder()
                .trainNumber("12004")
                .trainName("Lucknow Shatabdi Express")
                .trainType(TrainType.PASSENGER_EXPRESS)
                .priorityLevel(2)
                .originStation("NDLS")
                .destinationStation("LKO")
                .sourceSystem(SourceSystem.COA)
                .build());

        Train freight = trainRepository.save(Train.builder()
                .trainNumber("BCN-4021")
                .trainName("Coal Freight Special")
                .trainType(TrainType.FREIGHT)
                .priorityLevel(4)
                .originStation("MGS")
                .destinationStation("NDLS")
                .sourceSystem(SourceSystem.TDMS)
                .build());

        OffsetDateTime now = OffsetDateTime.now();
        trainScheduleRepository.save(TrainSchedule.builder()
                .train(rajdhani)
                .corridor(corridor1)
                .arrivalTime(now.plusHours(2))
                .departureTime(now.plusHours(5))
                .startKm(100.0)
                .endKm(200.0)
                .dayOfWeek("MONDAY")
                .frequencyPattern("DAILY")
                .build());

        trainScheduleRepository.save(TrainSchedule.builder()
                .train(shatabdi)
                .corridor(corridor1)
                .arrivalTime(now.plusHours(6))
                .departureTime(now.plusHours(8))
                .startKm(40.0)
                .endKm(90.0)
                .dayOfWeek("MONDAY")
                .frequencyPattern("DAILY")
                .build());

        BlockRequest req1 = blockRequestRepository.save(BlockRequest.builder()
                .requestNumber("BR-2026-001")
                .maintenanceTask(task1)
                .corridor(corridor1)
                .asset(oheAsset)
                .department(trd)
                .requestedByUser(engUser)
                .blockType(BlockType.POWER_BLOCK)
                .startTime(now.plusHours(3))
                .endTime(now.plusHours(6))
                .durationMinutes(180)
                .startKm(120.0)
                .endKm(135.0)
                .status(BlockRequestStatus.PENDING)
                .remarks("Overhead power block request for KM 120-135")
                .build());

        BlockRequest req2 = blockRequestRepository.save(BlockRequest.builder()
                .requestNumber("BR-2026-002")
                .maintenanceTask(task2)
                .corridor(corridor1)
                .asset(trackAsset)
                .department(engg)
                .requestedByUser(engUser)
                .blockType(BlockType.INTEGRATED_BLOCK)
                .startTime(now.plusHours(12))
                .endTime(now.plusHours(16))
                .durationMinutes(240)
                .startKm(45.0)
                .endKm(50.0)
                .status(BlockRequestStatus.PENDING)
                .remarks("Integrated track maintenance block")
                .build());

        conflictDetectionService.checkAndRecordConflicts(req1);
        conflictDetectionService.checkAndRecordConflicts(req2);

        blockPlanRepository.save(BlockPlan.builder()
                .planName("NDLS-CNB Corridor Weekly Maintenance Plan Week 37")
                .horizonType("WEEKLY")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(7))
                .status(PlanStatus.DRAFT)
                .createdByUser(planner)
                .build());

        log.info("Synthetic sample railway data seeded successfully!");
    }
}
