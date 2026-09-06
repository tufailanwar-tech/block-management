package com.railway.blockoptimizer.service;

import com.railway.blockoptimizer.domain.entity.Asset;
import com.railway.blockoptimizer.domain.entity.Department;
import com.railway.blockoptimizer.domain.entity.MaintenanceTask;
import com.railway.blockoptimizer.domain.enums.SourceSystem;
import com.railway.blockoptimizer.domain.enums.TaskStatus;
import com.railway.blockoptimizer.dto.MaintenanceTaskRequestDTO;
import com.railway.blockoptimizer.dto.MaintenanceTaskResponseDTO;
import com.railway.blockoptimizer.exception.ResourceNotFoundException;
import com.railway.blockoptimizer.repository.AssetRepository;
import com.railway.blockoptimizer.repository.DepartmentRepository;
import com.railway.blockoptimizer.repository.MaintenanceTaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MaintenanceTaskService {

    private final MaintenanceTaskRepository maintenanceTaskRepository;
    private final DepartmentRepository departmentRepository;
    private final AssetRepository assetRepository;

    public MaintenanceTaskService(MaintenanceTaskRepository maintenanceTaskRepository, DepartmentRepository departmentRepository, AssetRepository assetRepository) {
        this.maintenanceTaskRepository = maintenanceTaskRepository;
        this.departmentRepository = departmentRepository;
        this.assetRepository = assetRepository;
    }

    public List<MaintenanceTaskResponseDTO> getAllTasks() {
        return maintenanceTaskRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public MaintenanceTaskResponseDTO getTaskById(UUID id) {
        MaintenanceTask task = maintenanceTaskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance task not found with ID: " + id));
        return mapToResponse(task);
    }

    public MaintenanceTaskResponseDTO createTask(MaintenanceTaskRequestDTO dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + dto.getDepartmentId()));

        Asset asset = null;
        if (dto.getAssetId() != null) {
            asset = assetRepository.findById(dto.getAssetId())
                    .orElseThrow(() -> new ResourceNotFoundException("Asset not found with ID: " + dto.getAssetId()));
        }

        MaintenanceTask task = MaintenanceTask.builder()
                .department(department)
                .asset(asset)
                .taskType(dto.getTaskType().trim())
                .title(dto.getTitle().trim())
                .description(dto.getDescription())
                .priority(dto.getPriority())
                .estimatedDurationMinutes(dto.getEstimatedDurationMinutes())
                .requiredBlockType(dto.getRequiredBlockType())
                .periodicFrequency(dto.getPeriodicFrequency())
                .status(TaskStatus.SUBMITTED)
                .sourceSystem(dto.getSourceSystem() != null ? dto.getSourceSystem() : SourceSystem.MANUAL)
                .build();

        MaintenanceTask saved = maintenanceTaskRepository.save(task);
        return mapToResponse(saved);
    }

    public MaintenanceTaskResponseDTO updateTask(UUID id, MaintenanceTaskRequestDTO dto) {
        MaintenanceTask task = maintenanceTaskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance task not found with ID: " + id));

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + dto.getDepartmentId()));

        Asset asset = null;
        if (dto.getAssetId() != null) {
            asset = assetRepository.findById(dto.getAssetId())
                    .orElseThrow(() -> new ResourceNotFoundException("Asset not found with ID: " + dto.getAssetId()));
        }

        task.setDepartment(department);
        task.setAsset(asset);
        task.setTaskType(dto.getTaskType().trim());
        task.setTitle(dto.getTitle().trim());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setEstimatedDurationMinutes(dto.getEstimatedDurationMinutes());
        task.setRequiredBlockType(dto.getRequiredBlockType());
        task.setPeriodicFrequency(dto.getPeriodicFrequency());
        if (dto.getSourceSystem() != null) {
            task.setSourceSystem(dto.getSourceSystem());
        }

        MaintenanceTask updated = maintenanceTaskRepository.save(task);
        return mapToResponse(updated);
    }

    public MaintenanceTaskResponseDTO mapToResponse(MaintenanceTask task) {
        return MaintenanceTaskResponseDTO.builder()
                .id(task.getId())
                .departmentId(task.getDepartment() != null ? task.getDepartment().getId() : null)
                .departmentCode(task.getDepartment() != null ? task.getDepartment().getCode() : null)
                .departmentName(task.getDepartment() != null ? task.getDepartment().getName() : null)
                .assetId(task.getAsset() != null ? task.getAsset().getId() : null)
                .assetCode(task.getAsset() != null ? task.getAsset().getAssetCode() : null)
                .taskType(task.getTaskType())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .estimatedDurationMinutes(task.getEstimatedDurationMinutes())
                .requiredBlockType(task.getRequiredBlockType())
                .periodicFrequency(task.getPeriodicFrequency())
                .status(task.getStatus())
                .sourceSystem(task.getSourceSystem())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}
