package com.railway.blockoptimizer.dto;

import com.railway.blockoptimizer.domain.enums.BlockRequestStatus;
import com.railway.blockoptimizer.domain.enums.BlockType;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BlockRequestResponseDTO {
    private UUID id;
    private String requestNumber;
    private UUID maintenanceTaskId;
    private String maintenanceTaskTitle;
    private UUID corridorId;
    private String corridorCode;
    private UUID assetId;
    private String assetCode;
    private UUID departmentId;
    private String departmentCode;
    private UUID requestedByUserId;
    private String requestedByUserName;
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

    public BlockRequestResponseDTO() {}

    public BlockRequestResponseDTO(UUID id, String requestNumber, UUID maintenanceTaskId, String maintenanceTaskTitle, UUID corridorId, String corridorCode, UUID assetId, String assetCode, UUID departmentId, String departmentCode, UUID requestedByUserId, String requestedByUserName, BlockType blockType, OffsetDateTime startTime, OffsetDateTime endTime, Integer durationMinutes, Double startKm, Double endKm, BlockRequestStatus status, String remarks, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.requestNumber = requestNumber;
        this.maintenanceTaskId = maintenanceTaskId;
        this.maintenanceTaskTitle = maintenanceTaskTitle;
        this.corridorId = corridorId;
        this.corridorCode = corridorCode;
        this.assetId = assetId;
        this.assetCode = assetCode;
        this.departmentId = departmentId;
        this.departmentCode = departmentCode;
        this.requestedByUserId = requestedByUserId;
        this.requestedByUserName = requestedByUserName;
        this.blockType = blockType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationMinutes = durationMinutes;
        this.startKm = startKm;
        this.endKm = endKm;
        this.status = status;
        this.remarks = remarks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UUID id;
        private String requestNumber;
        private UUID maintenanceTaskId;
        private String maintenanceTaskTitle;
        private UUID corridorId;
        private String corridorCode;
        private UUID assetId;
        private String assetCode;
        private UUID departmentId;
        private String departmentCode;
        private UUID requestedByUserId;
        private String requestedByUserName;
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
        public Builder maintenanceTaskId(UUID maintenanceTaskId) { this.maintenanceTaskId = maintenanceTaskId; return this; }
        public Builder maintenanceTaskTitle(String maintenanceTaskTitle) { this.maintenanceTaskTitle = maintenanceTaskTitle; return this; }
        public Builder corridorId(UUID corridorId) { this.corridorId = corridorId; return this; }
        public Builder corridorCode(String corridorCode) { this.corridorCode = corridorCode; return this; }
        public Builder assetId(UUID assetId) { this.assetId = assetId; return this; }
        public Builder assetCode(String assetCode) { this.assetCode = assetCode; return this; }
        public Builder departmentId(UUID departmentId) { this.departmentId = departmentId; return this; }
        public Builder departmentCode(String departmentCode) { this.departmentCode = departmentCode; return this; }
        public Builder requestedByUserId(UUID requestedByUserId) { this.requestedByUserId = requestedByUserId; return this; }
        public Builder requestedByUserName(String requestedByUserName) { this.requestedByUserName = requestedByUserName; return this; }
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

        public BlockRequestResponseDTO build() {
            return new BlockRequestResponseDTO(id, requestNumber, maintenanceTaskId, maintenanceTaskTitle, corridorId, corridorCode, assetId, assetCode, departmentId, departmentCode, requestedByUserId, requestedByUserName, blockType, startTime, endTime, durationMinutes, startKm, endKm, status, remarks, createdAt, updatedAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getRequestNumber() { return requestNumber; }
    public void setRequestNumber(String requestNumber) { this.requestNumber = requestNumber; }

    public UUID getMaintenanceTaskId() { return maintenanceTaskId; }
    public void setMaintenanceTaskId(UUID maintenanceTaskId) { this.maintenanceTaskId = maintenanceTaskId; }

    public String getMaintenanceTaskTitle() { return maintenanceTaskTitle; }
    public void setMaintenanceTaskTitle(String maintenanceTaskTitle) { this.maintenanceTaskTitle = maintenanceTaskTitle; }

    public UUID getCorridorId() { return corridorId; }
    public void setCorridorId(UUID corridorId) { this.corridorId = corridorId; }

    public String getCorridorCode() { return corridorCode; }
    public void setCorridorCode(String corridorCode) { this.corridorCode = corridorCode; }

    public UUID getAssetId() { return assetId; }
    public void setAssetId(UUID assetId) { this.assetId = assetId; }

    public String getAssetCode() { return assetCode; }
    public void setAssetCode(String assetCode) { this.assetCode = assetCode; }

    public UUID getDepartmentId() { return departmentId; }
    public void setDepartmentId(UUID departmentId) { this.departmentId = departmentId; }

    public String getDepartmentCode() { return departmentCode; }
    public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }

    public UUID getRequestedByUserId() { return requestedByUserId; }
    public void setRequestedByUserId(UUID requestedByUserId) { this.requestedByUserId = requestedByUserId; }

    public String getRequestedByUserName() { return requestedByUserName; }
    public void setRequestedByUserName(String requestedByUserName) { this.requestedByUserName = requestedByUserName; }

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
