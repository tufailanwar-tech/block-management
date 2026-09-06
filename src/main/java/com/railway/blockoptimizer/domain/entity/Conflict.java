package com.railway.blockoptimizer.domain.entity;

import com.railway.blockoptimizer.domain.enums.ConflictSeverity;
import com.railway.blockoptimizer.domain.enums.ConflictType;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "conflicts")
public class Conflict {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_request_id")
    private BlockRequest blockRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conflicting_train_schedule_id")
    private TrainSchedule conflictingTrainSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conflicting_block_request_id")
    private BlockRequest conflictingBlockRequest;

    @Enumerated(EnumType.STRING)
    @Column(name = "conflict_type", nullable = false, length = 100)
    private ConflictType conflictType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ConflictSeverity severity;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Conflict() {}

    public Conflict(UUID id, BlockRequest blockRequest, TrainSchedule conflictingTrainSchedule, BlockRequest conflictingBlockRequest, ConflictType conflictType, ConflictSeverity severity, String details, String status, OffsetDateTime createdAt) {
        this.id = id;
        this.blockRequest = blockRequest;
        this.conflictingTrainSchedule = conflictingTrainSchedule;
        this.conflictingBlockRequest = conflictingBlockRequest;
        this.conflictType = conflictType;
        this.severity = severity;
        this.details = details;
        this.status = status;
        this.createdAt = createdAt != null ? createdAt : OffsetDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private BlockRequest blockRequest;
        private TrainSchedule conflictingTrainSchedule;
        private BlockRequest conflictingBlockRequest;
        private ConflictType conflictType;
        private ConflictSeverity severity;
        private String details;
        private String status;
        private OffsetDateTime createdAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder blockRequest(BlockRequest blockRequest) { this.blockRequest = blockRequest; return this; }
        public Builder conflictingTrainSchedule(TrainSchedule conflictingTrainSchedule) { this.conflictingTrainSchedule = conflictingTrainSchedule; return this; }
        public Builder conflictingBlockRequest(BlockRequest conflictingBlockRequest) { this.conflictingBlockRequest = conflictingBlockRequest; return this; }
        public Builder conflictType(ConflictType conflictType) { this.conflictType = conflictType; return this; }
        public Builder severity(ConflictSeverity severity) { this.severity = severity; return this; }
        public Builder details(String details) { this.details = details; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder createdAt(OffsetDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Conflict build() {
            return new Conflict(id, blockRequest, conflictingTrainSchedule, conflictingBlockRequest, conflictType, severity, details, status, createdAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public BlockRequest getBlockRequest() { return blockRequest; }
    public void setBlockRequest(BlockRequest blockRequest) { this.blockRequest = blockRequest; }

    public TrainSchedule getConflictingTrainSchedule() { return conflictingTrainSchedule; }
    public void setConflictingTrainSchedule(TrainSchedule conflictingTrainSchedule) { this.conflictingTrainSchedule = conflictingTrainSchedule; }

    public BlockRequest getConflictingBlockRequest() { return conflictingBlockRequest; }
    public void setConflictingBlockRequest(BlockRequest conflictingBlockRequest) { this.conflictingBlockRequest = conflictingBlockRequest; }

    public ConflictType getConflictType() { return conflictType; }
    public void setConflictType(ConflictType conflictType) { this.conflictType = conflictType; }

    public ConflictSeverity getSeverity() { return severity; }
    public void setSeverity(ConflictSeverity severity) { this.severity = severity; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
