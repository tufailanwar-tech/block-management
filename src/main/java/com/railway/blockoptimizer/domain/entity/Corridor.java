package com.railway.blockoptimizer.domain.entity;

import com.railway.blockoptimizer.domain.enums.CorridorStatus;
import com.railway.blockoptimizer.domain.enums.TrackType;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "corridors")
public class Corridor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(name = "source_station", nullable = false, length = 100)
    private String sourceStation;

    @Column(name = "destination_station", nullable = false, length = 100)
    private String destinationStation;

    @Column(name = "length_km", nullable = false)
    private Double lengthKm;

    @Column(name = "max_speed_kmh", nullable = false)
    private Integer maxSpeedKmh;

    @Enumerated(EnumType.STRING)
    @Column(name = "number_of_tracks", nullable = false, length = 50)
    private TrackType numberOfTracks;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private CorridorStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Corridor() {}

    public Corridor(UUID id, String code, String name, String sourceStation, String destinationStation, Double lengthKm, Integer maxSpeedKmh, TrackType numberOfTracks, CorridorStatus status, OffsetDateTime createdAt) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.lengthKm = lengthKm;
        this.maxSpeedKmh = maxSpeedKmh;
        this.numberOfTracks = numberOfTracks;
        this.status = status;
        this.createdAt = createdAt != null ? createdAt : OffsetDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String code;
        private String name;
        private String sourceStation;
        private String destinationStation;
        private Double lengthKm;
        private Integer maxSpeedKmh;
        private TrackType numberOfTracks;
        private CorridorStatus status;
        private OffsetDateTime createdAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder code(String code) { this.code = code; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder sourceStation(String sourceStation) { this.sourceStation = sourceStation; return this; }
        public Builder destinationStation(String destinationStation) { this.destinationStation = destinationStation; return this; }
        public Builder lengthKm(Double lengthKm) { this.lengthKm = lengthKm; return this; }
        public Builder maxSpeedKmh(Integer maxSpeedKmh) { this.maxSpeedKmh = maxSpeedKmh; return this; }
        public Builder numberOfTracks(TrackType numberOfTracks) { this.numberOfTracks = numberOfTracks; return this; }
        public Builder status(CorridorStatus status) { this.status = status; return this; }
        public Builder createdAt(OffsetDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Corridor build() {
            return new Corridor(id, code, name, sourceStation, destinationStation, lengthKm, maxSpeedKmh, numberOfTracks, status, createdAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSourceStation() { return sourceStation; }
    public void setSourceStation(String sourceStation) { this.sourceStation = sourceStation; }

    public String getDestinationStation() { return destinationStation; }
    public void setDestinationStation(String destinationStation) { this.destinationStation = destinationStation; }

    public Double getLengthKm() { return lengthKm; }
    public void setLengthKm(Double lengthKm) { this.lengthKm = lengthKm; }

    public Integer getMaxSpeedKmh() { return maxSpeedKmh; }
    public void setMaxSpeedKmh(Integer maxSpeedKmh) { this.maxSpeedKmh = maxSpeedKmh; }

    public TrackType getNumberOfTracks() { return numberOfTracks; }
    public void setNumberOfTracks(TrackType numberOfTracks) { this.numberOfTracks = numberOfTracks; }

    public CorridorStatus getStatus() { return status; }
    public void setStatus(CorridorStatus status) { this.status = status; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
