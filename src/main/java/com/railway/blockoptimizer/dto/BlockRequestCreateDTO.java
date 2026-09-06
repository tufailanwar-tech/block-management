package com.railway.blockoptimizer.dto;

import com.railway.blockoptimizer.domain.enums.BlockType;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BlockRequestCreateDTO {

    @NotNull(message = "Maintenance task ID is required")
    private UUID maintenanceTaskId;

    @NotNull(message = "Corridor ID is required")
    private UUID corridorId;

    private UUID assetId;

    @NotNull(message = "Department ID is required")
    private UUID departmentId;

    private UUID requestedByUserId;

    @NotNull(message = "Block type is required")
    private BlockType blockType;

    @NotNull(message = "Start time is required")
    private OffsetDateTime startTime;

    @NotNull(message = "End time is required")
    private OffsetDateTime endTime;

    @NotNull(message = "Start KM is required")
    private Double startKm;

    @NotNull(message = "End KM is required")
    private Double endKm;

    private String remarks;

    public BlockRequestCreateDTO() {}

    public BlockRequestCreateDTO(UUID maintenanceTaskId, UUID corridorId, UUID assetId, UUID departmentId, UUID requestedByUserId, BlockType blockType, OffsetDateTime startTime, OffsetDateTime endTime, Double startKm, Double endKm, String remarks) {
        this.maintenanceTaskId = maintenanceTaskId;
        this.corridorId = corridorId;
        this.assetId = assetId;
        this.departmentId = departmentId;
        this.requestedByUserId = requestedByUserId;
        this.blockType = blockType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startKm = startKm;
        this.endKm = endKm;
        this.remarks = remarks;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UUID maintenanceTaskId;
        private UUID corridorId;
        private UUID assetId;
        private UUID departmentId;
        private UUID requestedByUserId;
        private BlockType blockType;
        private OffsetDateTime startTime;
        private OffsetDateTime endTime;
        private Double startKm;
        private Double endKm;
        private String remarks;

        public Builder maintenanceTaskId(UUID maintenanceTaskId) { this.maintenanceTaskId = maintenanceTaskId; return this; }
        public Builder corridorId(UUID corridorId) { this.corridorId = corridorId; return this; }
        public Builder assetId(UUID assetId) { this.assetId = assetId; return this; }
        public Builder departmentId(UUID departmentId) { this.departmentId = departmentId; return this; }
        public Builder requestedByUserId(UUID requestedByUserId) { this.requestedByUserId = requestedByUserId; return this; }
        public Builder blockType(BlockType blockType) { this.blockType = blockType; return this; }
        public Builder startTime(OffsetDateTime startTime) { this.startTime = startTime; return this; }
        public Builder endTime(OffsetDateTime endTime) { this.endTime = endTime; return this; }
        public Builder startKm(Double startKm) { this.startKm = startKm; return this; }
        public Builder endKm(Double endKm) { this.endKm = endKm; return this; }
        public Builder remarks(String remarks) { this.remarks = remarks; return this; }

        public BlockRequestCreateDTO build() {
            return new BlockRequestCreateDTO(maintenanceTaskId, corridorId, assetId, departmentId, requestedByUserId, blockType, startTime, endTime, startKm, endKm, remarks);
        }
    }

    public UUID getMaintenanceTaskId() { return maintenanceTaskId; }
    public void setMaintenanceTaskId(UUID maintenanceTaskId) { this.maintenanceTaskId = maintenanceTaskId; }

    public UUID getCorridorId() { return corridorId; }
    public void setCorridorId(UUID corridorId) { this.corridorId = corridorId; }

    public UUID getAssetId() { return assetId; }
    public void setAssetId(UUID assetId) { this.assetId = assetId; }

    public UUID getDepartmentId() { return departmentId; }
    public void setDepartmentId(UUID departmentId) { this.departmentId = departmentId; }

    public UUID getRequestedByUserId() { return requestedByUserId; }
    public void setRequestedByUserId(UUID requestedByUserId) { this.requestedByUserId = requestedByUserId; }

    public BlockType getBlockType() { return blockType; }
    public void setBlockType(BlockType blockType) { this.blockType = blockType; }

    public OffsetDateTime getStartTime() { return startTime; }
    public void setStartTime(OffsetDateTime startTime) { this.startTime = startTime; }

    public OffsetDateTime getEndTime() { return endTime; }
    public void setEndTime(OffsetDateTime endTime) { this.endTime = endTime; }

    public Double getStartKm() { return startKm; }
    public void setStartKm(Double startKm) { this.startKm = startKm; }

    public Double getEndKm() { return endKm; }
    public void setEndKm(Double endKm) { this.endKm = endKm; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
