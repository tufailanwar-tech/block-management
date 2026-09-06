package com.railway.blockoptimizer.dto;

import com.railway.blockoptimizer.domain.enums.ConflictSeverity;
import com.railway.blockoptimizer.domain.enums.ConflictType;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ConflictDTO {
    private UUID id;
    private UUID blockRequestId;
    private String blockRequestNumber;
    private UUID conflictingTrainScheduleId;
    private String conflictingTrainNumber;
    private UUID conflictingBlockRequestId;
    private String conflictingBlockRequestNumber;
    private ConflictType conflictType;
    private ConflictSeverity severity;
    private String details;
    private String status;
    private OffsetDateTime createdAt;

    public ConflictDTO() {}

    public ConflictDTO(UUID id, UUID blockRequestId, String blockRequestNumber, UUID conflictingTrainScheduleId, String conflictingTrainNumber, UUID conflictingBlockRequestId, String conflictingBlockRequestNumber, ConflictType conflictType, ConflictSeverity severity, String details, String status, OffsetDateTime createdAt) {
        this.id = id;
        this.blockRequestId = blockRequestId;
        this.blockRequestNumber = blockRequestNumber;
        this.conflictingTrainScheduleId = conflictingTrainScheduleId;
        this.conflictingTrainNumber = conflictingTrainNumber;
        this.conflictingBlockRequestId = conflictingBlockRequestId;
        this.conflictingBlockRequestNumber = conflictingBlockRequestNumber;
        this.conflictType = conflictType;
        this.severity = severity;
        this.details = details;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UUID id;
        private UUID blockRequestId;
        private String blockRequestNumber;
        private UUID conflictingTrainScheduleId;
        private String conflictingTrainNumber;
        private UUID conflictingBlockRequestId;
        private String conflictingBlockRequestNumber;
        private ConflictType conflictType;
        private ConflictSeverity severity;
        private String details;
        private String status;
        private OffsetDateTime createdAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder blockRequestId(UUID blockRequestId) { this.blockRequestId = blockRequestId; return this; }
        public Builder blockRequestNumber(String blockRequestNumber) { this.blockRequestNumber = blockRequestNumber; return this; }
        public Builder conflictingTrainScheduleId(UUID conflictingTrainScheduleId) { this.conflictingTrainScheduleId = conflictingTrainScheduleId; return this; }
        public Builder conflictingTrainNumber(String conflictingTrainNumber) { this.conflictingTrainNumber = conflictingTrainNumber; return this; }
        public Builder conflictingBlockRequestId(UUID conflictingBlockRequestId) { this.conflictingBlockRequestId = conflictingBlockRequestId; return this; }
        public Builder conflictingBlockRequestNumber(String conflictingBlockRequestNumber) { this.conflictingBlockRequestNumber = conflictingBlockRequestNumber; return this; }
        public Builder conflictType(ConflictType conflictType) { this.conflictType = conflictType; return this; }
        public Builder severity(ConflictSeverity severity) { this.severity = severity; return this; }
        public Builder details(String details) { this.details = details; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder createdAt(OffsetDateTime createdAt) { this.createdAt = createdAt; return this; }

        public ConflictDTO build() {
            return new ConflictDTO(id, blockRequestId, blockRequestNumber, conflictingTrainScheduleId, conflictingTrainNumber, conflictingBlockRequestId, conflictingBlockRequestNumber, conflictType, severity, details, status, createdAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getBlockRequestId() { return blockRequestId; }
    public void setBlockRequestId(UUID blockRequestId) { this.blockRequestId = blockRequestId; }

    public String getBlockRequestNumber() { return blockRequestNumber; }
    public void setBlockRequestNumber(String blockRequestNumber) { this.blockRequestNumber = blockRequestNumber; }

    public UUID getConflictingTrainScheduleId() { return conflictingTrainScheduleId; }
    public void setConflictingTrainScheduleId(UUID conflictingTrainScheduleId) { this.conflictingTrainScheduleId = conflictingTrainScheduleId; }

    public String getConflictingTrainNumber() { return conflictingTrainNumber; }
    public void setConflictingTrainNumber(String conflictingTrainNumber) { this.conflictingTrainNumber = conflictingTrainNumber; }

    public UUID getConflictingBlockRequestId() { return conflictingBlockRequestId; }
    public void setConflictingBlockRequestId(UUID conflictingBlockRequestId) { this.conflictingBlockRequestId = conflictingBlockRequestId; }

    public String getConflictingBlockRequestNumber() { return conflictingBlockRequestNumber; }
    public void setConflictingBlockRequestNumber(String conflictingBlockRequestNumber) { this.conflictingBlockRequestNumber = conflictingBlockRequestNumber; }

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
