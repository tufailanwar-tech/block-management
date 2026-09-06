package com.railway.blockoptimizer.domain.entity;

import com.railway.blockoptimizer.domain.enums.BlockRequestStatus;
import com.railway.blockoptimizer.domain.enums.BlockType;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "block_requests")
public class BlockRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "request_number", nullable = false, unique = true, length = 50)
    private String requestNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_task_id", nullable = false)
    private MaintenanceTask maintenanceTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corridor_id", nullable = false)
    private Corridor corridor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requested_by_user_id")
    private User requestedByUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "block_type", nullable = false, length = 50)
    private BlockType blockType;

    @Column(name = "start_time", nullable = false)
    private OffsetDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private OffsetDateTime endTime;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @Column(name = "start_km", nullable = false)
    private Double startKm;

    @Column(name = "end_km", nullable = false)
    private Double endKm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private BlockRequestStatus status;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    public BlockRequest() {}

    public BlockRequest(UUID id, String requestNumber, MaintenanceTask maintenanceTask, Corridor corridor, Asset asset, Department department, User requestedByUser, BlockType blockType, OffsetDateTime startTime, OffsetDateTime endTime, Integer durationMinutes, Double startKm, Double endKm, BlockRequestStatus status, String remarks, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.requestNumber = requestNumber;
        this.maintenanceTask = maintenanceTask;
        this.corridor = corridor;
        this.asset = asset;
        this.department = department;
        this.requestedByUser = requestedByUser;
        this.blockType = blockType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationMinutes = durationMinutes;
        this.startKm = startKm;
        this.endKm = endKm;
        this.status = status;
        this.remarks = remarks;
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
        private String requestNumber;
        private MaintenanceTask maintenanceTask;
        private Corridor corridor;
        private Asset asset;
        private Department department;
        private User requestedByUser;
        private BlockType blockType;
        private OffsetDateTime startTime;
        private OffsetDateTime endTime;
        private Integer durationMinutes;
        private Double startKm;
        private Double endKm;
        private BlockRequestStatus status;
        private String remarks;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder requestNumber(String requestNumber) { this.requestNumber = requestNumber; return this; }
        public Builder maintenanceTask(MaintenanceTask maintenanceTask) { this.maintenanceTask = maintenanceTask; return this; }
        public Builder corridor(Corridor corridor) { this.corridor = corridor; return this; }
        public Builder asset(Asset asset) { this.asset = asset; return this; }
        public Builder department(Department department) { this.department = department; return this; }
        public Builder requestedByUser(User requestedByUser) { this.requestedByUser = requestedByUser; return this; }
        public Builder blockType(BlockType blockType) { this.blockType = blockType; return this; }
        public Builder startTime(OffsetDateTime startTime) { this.startTime = startTime; return this; }
        public Builder endTime(OffsetDateTime endTime) { this.endTime = endTime; return this; }
        public Builder durationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; return this; }
        public Builder startKm(Double startKm) { this.startKm = startKm; return this; }
        public Builder endKm(Double endKm) { this.endKm = endKm; return this; }
        public Builder status(BlockRequestStatus status) { this.status = status; return this; }
        public Builder remarks(String remarks) { this.remarks = remarks; return this; }
        public Builder createdAt(OffsetDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public BlockRequest build() {
            return new BlockRequest(id, requestNumber, maintenanceTask, corridor, asset, department, requestedByUser, blockType, startTime, endTime, durationMinutes, startKm, endKm, status, remarks, createdAt, updatedAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getRequestNumber() { return requestNumber; }
    public void setRequestNumber(String requestNumber) { this.requestNumber = requestNumber; }

    public MaintenanceTask getMaintenanceTask() { return maintenanceTask; }
    public void setMaintenanceTask(MaintenanceTask maintenanceTask) { this.maintenanceTask = maintenanceTask; }

    public Corridor getCorridor() { return corridor; }
    public void setCorridor(Corridor corridor) { this.corridor = corridor; }

    public Asset getAsset() { return asset; }
    public void setAsset(Asset asset) { this.asset = asset; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public User getRequestedByUser() { return requestedByUser; }
    public void setRequestedByUser(User requestedByUser) { this.requestedByUser = requestedByUser; }

    public BlockType getBlockType() { return blockType; }
    public void setBlockType(BlockType blockType) { this.blockType = blockType; }

    public OffsetDateTime getStartTime() { return startTime; }
    public void setStartTime(OffsetDateTime startTime) { this.startTime = startTime; }

    public OffsetDateTime getEndTime() { return endTime; }
    public void setEndTime(OffsetDateTime endTime) { this.endTime = endTime; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public Double getStartKm() { return startKm; }
    public void setStartKm(Double startKm) { this.startKm = startKm; }

    public Double getEndKm() { return endKm; }
    public void setEndKm(Double endKm) { this.endKm = endKm; }

    public BlockRequestStatus getStatus() { return status; }
    public void setStatus(BlockRequestStatus status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}
