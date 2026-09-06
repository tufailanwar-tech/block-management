package com.railway.blockoptimizer.dto;

import com.railway.blockoptimizer.domain.enums.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class MaintenanceTaskRequestDTO {

    @NotNull(message = "Department ID is required")
    private UUID departmentId;

    private UUID assetId;

    @NotBlank(message = "Task type is required")
    private String taskType;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Priority is required")
    private TaskPriority priority;

    @NotNull(message = "Estimated duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer estimatedDurationMinutes;

    @NotNull(message = "Required block type is required")
    private BlockType requiredBlockType;

    @NotNull(message = "Periodic frequency is required")
    private PeriodicFrequency periodicFrequency;

    private SourceSystem sourceSystem;

    public MaintenanceTaskRequestDTO() {}

    public MaintenanceTaskRequestDTO(UUID departmentId, UUID assetId, String taskType, String title, String description, TaskPriority priority, Integer estimatedDurationMinutes, BlockType requiredBlockType, PeriodicFrequency periodicFrequency, SourceSystem sourceSystem) {
        this.departmentId = departmentId;
        this.assetId = assetId;
        this.taskType = taskType;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
        this.requiredBlockType = requiredBlockType;
        this.periodicFrequency = periodicFrequency;
        this.sourceSystem = sourceSystem;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UUID departmentId;
        private UUID assetId;
        private String taskType;
        private String title;
        private String description;
        private TaskPriority priority;
        private Integer estimatedDurationMinutes;
        private BlockType requiredBlockType;
        private PeriodicFrequency periodicFrequency;
        private SourceSystem sourceSystem;

        public Builder departmentId(UUID departmentId) { this.departmentId = departmentId; return this; }
        public Builder assetId(UUID assetId) { this.assetId = assetId; return this; }
        public Builder taskType(String taskType) { this.taskType = taskType; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder priority(TaskPriority priority) { this.priority = priority; return this; }
        public Builder estimatedDurationMinutes(Integer estimatedDurationMinutes) { this.estimatedDurationMinutes = estimatedDurationMinutes; return this; }
        public Builder requiredBlockType(BlockType requiredBlockType) { this.requiredBlockType = requiredBlockType; return this; }
        public Builder periodicFrequency(PeriodicFrequency periodicFrequency) { this.periodicFrequency = periodicFrequency; return this; }
        public Builder sourceSystem(SourceSystem sourceSystem) { this.sourceSystem = sourceSystem; return this; }

        public MaintenanceTaskRequestDTO build() {
            return new MaintenanceTaskRequestDTO(departmentId, assetId, taskType, title, description, priority, estimatedDurationMinutes, requiredBlockType, periodicFrequency, sourceSystem);
        }
    }

    public UUID getDepartmentId() { return departmentId; }
    public void setDepartmentId(UUID departmentId) { this.departmentId = departmentId; }

    public UUID getAssetId() { return assetId; }
    public void setAssetId(UUID assetId) { this.assetId = assetId; }

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

    public SourceSystem getSourceSystem() { return sourceSystem; }
    public void setSourceSystem(SourceSystem sourceSystem) { this.sourceSystem = sourceSystem; }
}
