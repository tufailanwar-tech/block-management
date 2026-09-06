package com.railway.blockoptimizer.dto;

import com.railway.blockoptimizer.domain.enums.CorridorStatus;
import com.railway.blockoptimizer.domain.enums.TrackType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class CorridorDTO {
    private UUID id;

    @NotBlank(message = "Corridor code is required")
    private String code;

    @NotBlank(message = "Corridor name is required")
    private String name;

    @NotBlank(message = "Source station is required")
    private String sourceStation;

    @NotBlank(message = "Destination station is required")
    private String destinationStation;

    @NotNull(message = "Length in KM is required")
    private Double lengthKm;

    @NotNull(message = "Max speed is required")
    private Integer maxSpeedKmh;

    @NotNull(message = "Number of tracks is required")
    private TrackType numberOfTracks;

    @NotNull(message = "Status is required")
    private CorridorStatus status;

    public CorridorDTO() {}

    public CorridorDTO(UUID id, String code, String name, String sourceStation, String destinationStation, Double lengthKm, Integer maxSpeedKmh, TrackType numberOfTracks, CorridorStatus status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.lengthKm = lengthKm;
        this.maxSpeedKmh = maxSpeedKmh;
        this.numberOfTracks = numberOfTracks;
        this.status = status;
    }

    public static Builder builder() { return new Builder(); }

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

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder code(String code) { this.code = code; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder sourceStation(String sourceStation) { this.sourceStation = sourceStation; return this; }
        public Builder destinationStation(String destinationStation) { this.destinationStation = destinationStation; return this; }
        public Builder lengthKm(Double lengthKm) { this.lengthKm = lengthKm; return this; }
        public Builder maxSpeedKmh(Integer maxSpeedKmh) { this.maxSpeedKmh = maxSpeedKmh; return this; }
        public Builder numberOfTracks(TrackType numberOfTracks) { this.numberOfTracks = numberOfTracks; return this; }
        public Builder status(CorridorStatus status) { this.status = status; return this; }

        public CorridorDTO build() {
            return new CorridorDTO(id, code, name, sourceStation, destinationStation, lengthKm, maxSpeedKmh, numberOfTracks, status);
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
}
