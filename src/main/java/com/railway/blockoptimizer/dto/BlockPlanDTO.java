package com.railway.blockoptimizer.dto;

import com.railway.blockoptimizer.domain.enums.PlanStatus;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class BlockPlanDTO {
    private UUID id;
    private String planName;
    private String horizonType;
    private LocalDate startDate;
    private LocalDate endDate;
    private PlanStatus status;
    private UUID createdByUserId;
    private String createdByUserName;
    private UUID approvedByUserId;
    private String approvedByUserName;
    private List<BlockRequestResponseDTO> scheduledTasks;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public BlockPlanDTO() {}

    public BlockPlanDTO(UUID id, String planName, String horizonType, LocalDate startDate, LocalDate endDate, PlanStatus status, UUID createdByUserId, String createdByUserName, UUID approvedByUserId, String approvedByUserName, List<BlockRequestResponseDTO> scheduledTasks, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.planName = planName;
        this.horizonType = horizonType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdByUserId = createdByUserId;
        this.createdByUserName = createdByUserName;
        this.approvedByUserId = approvedByUserId;
        this.approvedByUserName = approvedByUserName;
        this.scheduledTasks = scheduledTasks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UUID id;
        private String planName;
        private String horizonType;
        private LocalDate startDate;
        private LocalDate endDate;
        private PlanStatus status;
        private UUID createdByUserId;
        private String createdByUserName;
        private UUID approvedByUserId;
        private String approvedByUserName;
        private List<BlockRequestResponseDTO> scheduledTasks;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder planName(String planName) { this.planName = planName; return this; }
        public Builder horizonType(String horizonType) { this.horizonType = horizonType; return this; }
        public Builder startDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public Builder endDate(LocalDate endDate) { this.endDate = endDate; return this; }
        public Builder status(PlanStatus status) { this.status = status; return this; }
        public Builder createdByUserId(UUID createdByUserId) { this.createdByUserId = createdByUserId; return this; }
        public Builder createdByUserName(String createdByUserName) { this.createdByUserName = createdByUserName; return this; }
        public Builder approvedByUserId(UUID approvedByUserId) { this.approvedByUserId = approvedByUserId; return this; }
        public Builder approvedByUserName(String approvedByUserName) { this.approvedByUserName = approvedByUserName; return this; }
        public Builder scheduledTasks(List<BlockRequestResponseDTO> scheduledTasks) { this.scheduledTasks = scheduledTasks; return this; }
        public Builder createdAt(OffsetDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public BlockPlanDTO build() {
            return new BlockPlanDTO(id, planName, horizonType, startDate, endDate, status, createdByUserId, createdByUserName, approvedByUserId, approvedByUserName, scheduledTasks, createdAt, updatedAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public String getHorizonType() { return horizonType; }
    public void setHorizonType(String horizonType) { this.horizonType = horizonType; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public PlanStatus getStatus() { return status; }
    public void setStatus(PlanStatus status) { this.status = status; }

    public UUID getCreatedByUserId() { return createdByUserId; }
    public void setCreatedByUserId(UUID createdByUserId) { this.createdByUserId = createdByUserId; }

    public String getCreatedByUserName() { return createdByUserName; }
    public void setCreatedByUserName(String createdByUserName) { this.createdByUserName = createdByUserName; }

    public UUID getApprovedByUserId() { return approvedByUserId; }
    public void setApprovedByUserId(UUID approvedByUserId) { this.approvedByUserId = approvedByUserId; }

    public String getApprovedByUserName() { return approvedByUserName; }
    public void setApprovedByUserName(String approvedByUserName) { this.approvedByUserName = approvedByUserName; }

    public List<BlockRequestResponseDTO> getScheduledTasks() { return scheduledTasks; }
    public void setScheduledTasks(List<BlockRequestResponseDTO> scheduledTasks) { this.scheduledTasks = scheduledTasks; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}
