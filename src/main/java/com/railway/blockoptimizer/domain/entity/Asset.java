package com.railway.blockoptimizer.domain.entity;

import com.railway.blockoptimizer.domain.enums.AssetStatus;
import com.railway.blockoptimizer.domain.enums.AssetType;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corridor_id", nullable = false)
    private Corridor corridor;

    @Column(name = "asset_code", nullable = false, unique = true, length = 50)
    private String assetCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "asset_type", nullable = false, length = 50)
    private AssetType assetType;

    @Column(name = "location_km_start", nullable = false)
    private Double locationKmStart;

    @Column(name = "location_km_end", nullable = false)
    private Double locationKmEnd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private AssetStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Asset() {}

    public Asset(UUID id, Corridor corridor, String assetCode, AssetType assetType, Double locationKmStart, Double locationKmEnd, AssetStatus status, OffsetDateTime createdAt) {
        this.id = id;
        this.corridor = corridor;
        this.assetCode = assetCode;
        this.assetType = assetType;
        this.locationKmStart = locationKmStart;
        this.locationKmEnd = locationKmEnd;
        this.status = status;
        this.createdAt = createdAt != null ? createdAt : OffsetDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private Corridor corridor;
        private String assetCode;
        private AssetType assetType;
        private Double locationKmStart;
        private Double locationKmEnd;
        private AssetStatus status;
        private OffsetDateTime createdAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder corridor(Corridor corridor) { this.corridor = corridor; return this; }
        public Builder assetCode(String assetCode) { this.assetCode = assetCode; return this; }
        public Builder assetType(AssetType assetType) { this.assetType = assetType; return this; }
        public Builder locationKmStart(Double locationKmStart) { this.locationKmStart = locationKmStart; return this; }
        public Builder locationKmEnd(Double locationKmEnd) { this.locationKmEnd = locationKmEnd; return this; }
        public Builder status(AssetStatus status) { this.status = status; return this; }
        public Builder createdAt(OffsetDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Asset build() {
            return new Asset(id, corridor, assetCode, assetType, locationKmStart, locationKmEnd, status, createdAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Corridor getCorridor() { return corridor; }
    public void setCorridor(Corridor corridor) { this.corridor = corridor; }

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

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
