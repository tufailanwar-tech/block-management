package com.railway.blockoptimizer.domain.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "block_plan_tasks")
public class BlockPlanTask {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_plan_id", nullable = false)
    private BlockPlan blockPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_request_id", nullable = false)
    private BlockRequest blockRequest;

    @Column(name = "scheduled_start_time", nullable = false)
    private OffsetDateTime scheduledStartTime;

    @Column(name = "scheduled_end_time", nullable = false)
    private OffsetDateTime scheduledEndTime;

    @Column(name = "assigned_priority", nullable = false, length = 50)
    private String assignedPriority;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public BlockPlanTask() {}

    public BlockPlanTask(UUID id, BlockPlan blockPlan, BlockRequest blockRequest, OffsetDateTime scheduledStartTime, OffsetDateTime scheduledEndTime, String assignedPriority, String status, OffsetDateTime createdAt) {
        this.id = id;
        this.blockPlan = blockPlan;
        this.blockRequest = blockRequest;
        this.scheduledStartTime = scheduledStartTime;
        this.scheduledEndTime = scheduledEndTime;
        this.assignedPriority = assignedPriority;
        this.status = status;
        this.createdAt = createdAt != null ? createdAt : OffsetDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private BlockPlan blockPlan;
        private BlockRequest blockRequest;
        private OffsetDateTime scheduledStartTime;
        private OffsetDateTime scheduledEndTime;
        private String assignedPriority;
        private String status;
        private OffsetDateTime createdAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder blockPlan(BlockPlan blockPlan) { this.blockPlan = blockPlan; return this; }
        public Builder blockRequest(BlockRequest blockRequest) { this.blockRequest = blockRequest; return this; }
        public Builder scheduledStartTime(OffsetDateTime scheduledStartTime) { this.scheduledStartTime = scheduledStartTime; return this; }
        public Builder scheduledEndTime(OffsetDateTime scheduledEndTime) { this.scheduledEndTime = scheduledEndTime; return this; }
        public Builder assignedPriority(String assignedPriority) { this.assignedPriority = assignedPriority; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder createdAt(OffsetDateTime createdAt) { this.createdAt = createdAt; return this; }

        public BlockPlanTask build() {
            return new BlockPlanTask(id, blockPlan, blockRequest, scheduledStartTime, scheduledEndTime, assignedPriority, status, createdAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public BlockPlan getBlockPlan() { return blockPlan; }
    public void setBlockPlan(BlockPlan blockPlan) { this.blockPlan = blockPlan; }

    public BlockRequest getBlockRequest() { return blockRequest; }
    public void setBlockRequest(BlockRequest blockRequest) { this.blockRequest = blockRequest; }

    public OffsetDateTime getScheduledStartTime() { return scheduledStartTime; }
    public void setScheduledStartTime(OffsetDateTime scheduledStartTime) { this.scheduledStartTime = scheduledStartTime; }

    public OffsetDateTime getScheduledEndTime() { return scheduledEndTime; }
    public void setScheduledEndTime(OffsetDateTime scheduledEndTime) { this.scheduledEndTime = scheduledEndTime; }

    public String getAssignedPriority() { return assignedPriority; }
    public void setAssignedPriority(String assignedPriority) { this.assignedPriority = assignedPriority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
