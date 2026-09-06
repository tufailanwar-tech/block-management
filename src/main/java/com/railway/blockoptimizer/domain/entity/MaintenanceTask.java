package com.railway.blockoptimizer.domain.entity;

import com.railway.blockoptimizer.domain.enums.*;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "maintenance_tasks")
public class MaintenanceTask {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @Column(name = "task_type", nullable = false, length = 100)
    private String taskType;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TaskPriority priority;

    @Column(name = "estimated_duration_minutes", nullable = false)
    private Integer estimatedDurationMinutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "required_block_type", nullable = false, length = 50)
    private BlockType requiredBlockType;

    @Enumerated(EnumType.STRING)
    @Column(name = "periodic_frequency", nullable = false, length = 50)
    private PeriodicFrequency periodicFrequency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_system", nullable = false, length = 50)
    private SourceSystem sourceSystem;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    public MaintenanceTask() {}

    public MaintenanceTask(UUID id, Department department, Asset asset, String taskType, String title, String description, TaskPriority priority, Integer estimatedDurationMinutes, BlockType requiredBlockType, PeriodicFrequency periodicFrequency, TaskStatus status, SourceSystem sourceSystem, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.department = department;
        this.asset = asset;
        this.taskType = taskType;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
        this.requiredBlockType = requiredBlockType;
        this.periodicFrequency = periodicFrequency;
        this.status = status;
        this.sourceSystem = sourceSystem;
        this.createdAt = createdAt != null ? createdAt : OffsetDateTime.now();
        this.updatedAt = updatedAt != null ? updatedAt : OffsetDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private Department department;
        private Asset asset;
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
        public Builder department(Department department) { this.department = department; return this; }
        public Builder asset(Asset asset) { this.asset = asset; return this; }
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

        public MaintenanceTask build() {
            return new MaintenanceTask(id, department, asset, taskType, title, description, priority, estimatedDurationMinutes, requiredBlockType, periodicFrequency, status, sourceSystem, createdAt, updatedAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public Asset getAsset() { return asset; }
    public void setAsset(Asset asset) { this.asset = asset; }

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
