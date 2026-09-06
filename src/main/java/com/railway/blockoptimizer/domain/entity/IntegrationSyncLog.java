package com.railway.blockoptimizer.domain.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "integration_sync_logs")
public class IntegrationSyncLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "system_name", nullable = false, length = 50)
    private String systemName;

    @Column(name = "sync_type", nullable = false, length = 50)
    private String syncType;

    @Column(name = "records_processed", nullable = false)
    private Integer recordsProcessed;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "error_details", columnDefinition = "TEXT")
    private String errorDetails;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime timestamp = OffsetDateTime.now();

    public IntegrationSyncLog() {}

    public IntegrationSyncLog(UUID id, String systemName, String syncType, Integer recordsProcessed, String status, String errorDetails, OffsetDateTime timestamp) {
        this.id = id;
        this.systemName = systemName;
        this.syncType = syncType;
        this.recordsProcessed = recordsProcessed;
        this.status = status;
        this.errorDetails = errorDetails;
        this.timestamp = timestamp != null ? timestamp : OffsetDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UUID id;
        private String systemName;
        private String syncType;
        private Integer recordsProcessed;
        private String status;
        private String errorDetails;
        private OffsetDateTime timestamp;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder systemName(String systemName) { this.systemName = systemName; return this; }
        public Builder syncType(String syncType) { this.syncType = syncType; return this; }
        public Builder recordsProcessed(Integer recordsProcessed) { this.recordsProcessed = recordsProcessed; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder errorDetails(String errorDetails) { this.errorDetails = errorDetails; return this; }
        public Builder timestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; return this; }

        public IntegrationSyncLog build() {
            return new IntegrationSyncLog(id, systemName, syncType, recordsProcessed, status, errorDetails, timestamp);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getSystemName() { return systemName; }
    public void setSystemName(String systemName) { this.systemName = systemName; }

    public String getSyncType() { return syncType; }
    public void setSyncType(String syncType) { this.syncType = syncType; }

    public Integer getRecordsProcessed() { return recordsProcessed; }
    public void setRecordsProcessed(Integer recordsProcessed) { this.recordsProcessed = recordsProcessed; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getErrorDetails() { return errorDetails; }
    public void setErrorDetails(String errorDetails) { this.errorDetails = errorDetails; }

    public OffsetDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; }
}
