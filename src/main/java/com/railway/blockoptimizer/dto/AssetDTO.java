package com.railway.blockoptimizer.dto;

import com.railway.blockoptimizer.domain.enums.AssetStatus;
import com.railway.blockoptimizer.domain.enums.AssetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class AssetDTO {
    private UUID id;

    @NotNull(message = "Corridor ID is required")
    private UUID corridorId;
    private String corridorCode;

    @NotBlank(message = "Asset code is required")
    private String assetCode;

    @NotNull(message = "Asset type is required")
    private AssetType assetType;

    @NotNull(message = "Location start KM is required")
    private Double locationKmStart;

    @NotNull(message = "Location end KM is required")
    private Double locationKmEnd;

    @NotNull(message = "Status is required")
    private AssetStatus status;

    public AssetDTO() {}

    public AssetDTO(UUID id, UUID corridorId, String corridorCode, String assetCode, AssetType assetType, Double locationKmStart, Double locationKmEnd, AssetStatus status) {
        this.id = id;
        this.corridorId = corridorId;
        this.corridorCode = corridorCode;
        this.assetCode = assetCode;
        this.assetType = assetType;
        this.locationKmStart = locationKmStart;
        this.locationKmEnd = locationKmEnd;
        this.status = status;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UUID id;
        private UUID corridorId;
        private String corridorCode;
        private String assetCode;
        private AssetType assetType;
        private Double locationKmStart;
        private Double locationKmEnd;
        private AssetStatus status;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder corridorId(UUID corridorId) { this.corridorId = corridorId; return this; }
        public Builder corridorCode(String corridorCode) { this.corridorCode = corridorCode; return this; }
        public Builder assetCode(String assetCode) { this.assetCode = assetCode; return this; }
        public Builder assetType(AssetType assetType) { this.assetType = assetType; return this; }
        public Builder locationKmStart(Double locationKmStart) { this.locationKmStart = locationKmStart; return this; }
        public Builder locationKmEnd(Double locationKmEnd) { this.locationKmEnd = locationKmEnd; return this; }
        public Builder status(AssetStatus status) { this.status = status; return this; }

        public AssetDTO build() {
            return new AssetDTO(id, corridorId, corridorCode, assetCode, assetType, locationKmStart, locationKmEnd, status);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getCorridorId() { return corridorId; }
    public void setCorridorId(UUID corridorId) { this.corridorId = corridorId; }

    public String getCorridorCode() { return corridorCode; }
    public void setCorridorCode(String corridorCode) { this.corridorCode = corridorCode; }

    public String getAssetCode() { return assetCode; }
    public void setAssetCode(String assetCode) { this.assetCode = assetCode; }

    public AssetType getAssetType() { return assetType; }
    public void setAssetType(AssetType assetType) { this.assetType = assetType; }

    public Double getLocationKmStart() { return locationKmStart; }
    public void setLocationKmStart(Double locationKmStart) { this.locationKmStart = locationKmStart; }

    public Double getLocationKmEnd() { return locationKmEnd; }
    public void setLocationKmEnd(Double locationKmEnd) { this.locationKmEnd = locationKmEnd; }

    public AssetStatus getStatus() { return status; }
    public void setStatus(AssetStatus status) { this.status = status; }
}
