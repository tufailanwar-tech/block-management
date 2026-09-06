package com.railway.blockoptimizer.domain.entity;

import com.railway.blockoptimizer.domain.enums.PlanStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "block_plans")
public class BlockPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Column(name = "horizon_type", nullable = false, length = 50)
    private String horizonType;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private PlanStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id")
    private User createdByUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by_user_id")
    private User approvedByUser;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    public BlockPlan() {}

    public BlockPlan(UUID id, String planName, String horizonType, LocalDate startDate, LocalDate endDate, PlanStatus status, User createdByUser, User approvedByUser, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.planName = planName;
        this.horizonType = horizonType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdByUser = createdByUser;
        this.approvedByUser = approvedByUser;
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
        private String planName;
        private String horizonType;
        private LocalDate startDate;
        private LocalDate endDate;
        private PlanStatus status;
        private User createdByUser;
        private User approvedByUser;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder planName(String planName) { this.planName = planName; return this; }
        public Builder horizonType(String horizonType) { this.horizonType = horizonType; return this; }
        public Builder startDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public Builder endDate(LocalDate endDate) { this.endDate = endDate; return this; }
        public Builder status(PlanStatus status) { this.status = status; return this; }
        public Builder createdByUser(User createdByUser) { this.createdByUser = createdByUser; return this; }
        public Builder approvedByUser(User approvedByUser) { this.approvedByUser = approvedByUser; return this; }
        public Builder createdAt(OffsetDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public BlockPlan build() {
            return new BlockPlan(id, planName, horizonType, startDate, endDate, status, createdByUser, approvedByUser, createdAt, updatedAt);
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

    public User getCreatedByUser() { return createdByUser; }
    public void setCreatedByUser(User createdByUser) { this.createdByUser = createdByUser; }

    public User getApprovedByUser() { return approvedByUser; }
    public void setApprovedByUser(User approvedByUser) { this.approvedByUser = approvedByUser; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}
