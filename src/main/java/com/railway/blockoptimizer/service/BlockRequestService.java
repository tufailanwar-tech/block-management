package com.railway.blockoptimizer.service;

import com.railway.blockoptimizer.domain.entity.*;
import com.railway.blockoptimizer.domain.enums.BlockRequestStatus;
import com.railway.blockoptimizer.dto.BlockRequestCreateDTO;
import com.railway.blockoptimizer.dto.BlockRequestResponseDTO;
import com.railway.blockoptimizer.exception.ResourceNotFoundException;
import com.railway.blockoptimizer.exception.ValidationException;
import com.railway.blockoptimizer.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
public class BlockRequestService {

    private final BlockRequestRepository blockRequestRepository;
    private final MaintenanceTaskRepository maintenanceTaskRepository;
    private final CorridorRepository corridorRepository;
    private final AssetRepository assetRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final ConflictDetectionService conflictDetectionService;

    public BlockRequestService(BlockRequestRepository blockRequestRepository, MaintenanceTaskRepository maintenanceTaskRepository, CorridorRepository corridorRepository, AssetRepository assetRepository, DepartmentRepository departmentRepository, UserRepository userRepository, ConflictDetectionService conflictDetectionService) {
        this.blockRequestRepository = blockRequestRepository;
        this.maintenanceTaskRepository = maintenanceTaskRepository;
        this.corridorRepository = corridorRepository;
        this.assetRepository = assetRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.conflictDetectionService = conflictDetectionService;
    }

    public List<BlockRequestResponseDTO> getAllBlockRequests() {
        return blockRequestRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public BlockRequestResponseDTO getBlockRequestById(UUID id) {
        BlockRequest br = blockRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Block request not found with ID: " + id));
        return mapToResponse(br);
    }

    @Transactional
    public BlockRequestResponseDTO createBlockRequest(BlockRequestCreateDTO dto) {
        if (dto.getEndTime().isBefore(dto.getStartTime()) || dto.getEndTime().isEqual(dto.getStartTime())) {
            throw new ValidationException("End time must be after start time");
        }
        if (dto.getStartKm() < 0 || dto.getEndKm() <= dto.getStartKm()) {
            throw new ValidationException("Invalid spatial location range: endKm must be greater than startKm");
        }

        MaintenanceTask task = maintenanceTaskRepository.findById(dto.getMaintenanceTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance task not found: " + dto.getMaintenanceTaskId()));

        Corridor corridor = corridorRepository.findById(dto.getCorridorId())
                .orElseThrow(() -> new ResourceNotFoundException("Corridor not found: " + dto.getCorridorId()));

        if (dto.getEndKm() > corridor.getLengthKm()) {
            throw new ValidationException(String.format("Location endKm (%.2f) exceeds corridor total length (%.2f)",
                    dto.getEndKm(), corridor.getLengthKm()));
        }

        Asset asset = null;
        if (dto.getAssetId() != null) {
            asset = assetRepository.findById(dto.getAssetId())
                    .orElseThrow(() -> new ResourceNotFoundException("Asset not found: " + dto.getAssetId()));
        }

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + dto.getDepartmentId()));

        User user = null;
        if (dto.getRequestedByUserId() != null) {
            user = userRepository.findById(dto.getRequestedByUserId()).orElse(null);
        }

        int durationMinutes = (int) Duration.between(dto.getStartTime(), dto.getEndTime()).toMinutes();
        String requestNumber = "BR-" + System.currentTimeMillis() % 1000000;

        BlockRequest blockRequest = BlockRequest.builder()
                .requestNumber(requestNumber)
                .maintenanceTask(task)
                .corridor(corridor)
                .asset(asset)
                .department(department)
                .requestedByUser(user)
                .blockType(dto.getBlockType())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .durationMinutes(durationMinutes)
                .startKm(dto.getStartKm())
                .endKm(dto.getEndKm())
                .status(BlockRequestStatus.PENDING)
                .remarks(dto.getRemarks())
                .build();

        BlockRequest saved = blockRequestRepository.save(blockRequest);
        conflictDetectionService.checkAndRecordConflicts(saved);

        return mapToResponse(saved);
    }

    @Transactional
    public BlockRequestResponseDTO updateStatus(UUID id, BlockRequestStatus status, String remarks) {
        BlockRequest br = blockRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Block request not found: " + id));

        br.setStatus(status);
        if (remarks != null && !remarks.isBlank()) {
            br.setRemarks(br.getRemarks() != null ? br.getRemarks() + " | " + remarks : remarks);
        }
        BlockRequest updated = blockRequestRepository.save(br);
        return mapToResponse(updated);
    }

    public BlockRequestResponseDTO mapToResponse(BlockRequest br) {
        return BlockRequestResponseDTO.builder()
                .id(br.getId())
                .requestNumber(br.getRequestNumber())
                .maintenanceTaskId(br.getMaintenanceTask() != null ? br.getMaintenanceTask().getId() : null)
                .maintenanceTaskTitle(br.getMaintenanceTask() != null ? br.getMaintenanceTask().getTitle() : null)
                .corridorId(br.getCorridor() != null ? br.getCorridor().getId() : null)
                .corridorCode(br.getCorridor() != null ? br.getCorridor().getCode() : null)
                .assetId(br.getAsset() != null ? br.getAsset().getId() : null)
                .assetCode(br.getAsset() != null ? br.getAsset().getAssetCode() : null)
                .departmentId(br.getDepartment() != null ? br.getDepartment().getId() : null)
                .departmentCode(br.getDepartment() != null ? br.getDepartment().getCode() : null)
                .requestedByUserId(br.getRequestedByUser() != null ? br.getRequestedByUser().getId() : null)
                .requestedByUserName(br.getRequestedByUser() != null ? br.getRequestedByUser().getFullName() : null)
                .blockType(br.getBlockType())
                .startTime(br.getStartTime())
                .endTime(br.getEndTime())
                .durationMinutes(br.getDurationMinutes())
                .startKm(br.getStartKm())
                .endKm(br.getEndKm())
                .status(br.getStatus())
                .remarks(br.getRemarks())
                .createdAt(br.getCreatedAt())
                .updatedAt(br.getUpdatedAt())
                .build();
    }
}
