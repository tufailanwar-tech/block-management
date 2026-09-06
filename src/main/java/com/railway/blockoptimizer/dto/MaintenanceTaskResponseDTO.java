package com.railway.blockoptimizer.dto;

import com.railway.blockoptimizer.domain.enums.*;
import java.time.OffsetDateTime;
import java.util.UUID;

public class MaintenanceTaskResponseDTO {
    private UUID id;
    private UUID departmentId;
    private String departmentCode;
    private String departmentName;
    private UUID assetId;
    private String assetCode;
    private String taskType;
    private String title;
    private String description;
    private TaskPriority priority;
    private Integer estimatedDurationMinutes;
    private BlockType requiredBlockType;
    private PeriodicFrequency periodicFrequency;
    private TaskStatus status;
    private SourceSystem sourceSystem;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public MaintenanceTaskResponseDTO() {}

    public MaintenanceTaskResponseDTO(UUID id, UUID departmentId, String departmentCode, String departmentName, UUID assetId, String assetCode, String taskType, String title, String description, TaskPriority priority, Integer estimatedDurationMinutes, BlockType requiredBlockType, PeriodicFrequency periodicFrequency, TaskStatus status, SourceSystem sourceSystem, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.departmentId = departmentId;
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.assetId = assetId;
        this.assetCode = assetCode;
        this.taskType = taskType;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
        this.requiredBlockType = requiredBlockType;
        this.periodicFrequency = periodicFrequency;
        this.status = status;
        this.sourceSystem = sourceSystem;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UUID id;
        private UUID departmentId;
        private String departmentCode;
        private String departmentName;
        private UUID assetId;
        private String assetCode;
        private String taskType;
        private String title;
        private String description;
        private TaskPriority priority;
        private Integer estimatedDurationMinutes;
        private BlockType requiredBlockType;
        private PeriodicFrequency periodicFrequency;
        private TaskStatus status;
        private SourceSystem sourceSystem;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder departmentId(UUID departmentId) { this.departmentId = departmentId; return this; }
        public Builder departmentCode(String departmentCode) { this.departmentCode = departmentCode; return this; }
        public Builder departmentName(String departmentName) { this.departmentName = departmentName; return this; }
        public Builder assetId(UUID assetId) { this.assetId = assetId; return this; }
        public Builder assetCode(String assetCode) { this.assetCode = assetCode; return this; }
        public Builder taskType(String taskType) { this.taskType = taskType; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder priority(TaskPriority priority) { this.priority = priority; return this; }
        public Builder estimatedDurationMinutes(Integer estimatedDurationMinutes) { this.estimatedDurationMinutes = estimatedDurationMinutes; return this; }
        public Builder requiredBlockType(BlockType requiredBlockType) { this.requiredBlockType = requiredBlockType; return this; }
        public Builder periodicFrequency(PeriodicFrequency periodicFrequency) { this.periodicFrequency = periodicFrequency; return this; }
        public Builder status(TaskStatus status) { this.status = status; return this; }
        public Builder sourceSystem(SourceSystem sourceSystem) { this.sourceSystem = sourceSystem; return this; }
        public Builder createdAt(OffsetDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public MaintenanceTaskResponseDTO build() {
            return new MaintenanceTaskResponseDTO(id, departmentId, departmentCode, departmentName, assetId, assetCode, taskType, title, description, priority, estimatedDurationMinutes, requiredBlockType, periodicFrequency, status, sourceSystem, createdAt, updatedAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getDepartmentId() { return departmentId; }
    public void setDepartmentId(UUID departmentId) { this.departmentId = departmentId; }

    public String getDepartmentCode() { return departmentCode; }
    public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public UUID getAssetId() { return assetId; }
    public void setAssetId(UUID assetId) { this.assetId = assetId; }

    public String getAssetCode() { return assetCode; }
    public void setAssetCode(String assetCode) { this.assetCode = assetCode; }

    public String getTaskType() { return taskType; }
    public void setTaskType(String taskType) { this.taskType = taskType; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskPriority getPriority() { return priority; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }

    public Integer getEstimatedDurationMinutes() { return estimatedDurationMinutes; }
    public void setEstimatedDurationMinutes(Integer estimatedDurationMinutes) { this.estimatedDurationMinutes = estimatedDurationMinutes; }

    public BlockType getRequiredBlockType() { return requiredBlockType; }
    public void setRequiredBlockType(BlockType requiredBlockType) { this.requiredBlockType = requiredBlockType; }

    public PeriodicFrequency getPeriodicFrequency() { return periodicFrequency; }
    public void setPeriodicFrequency(PeriodicFrequency periodicFrequency) { this.periodicFrequency = periodicFrequency; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public SourceSystem getSourceSystem() { return sourceSystem; }
    public void setSourceSystem(SourceSystem sourceSystem) { this.sourceSystem = sourceSystem; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}
