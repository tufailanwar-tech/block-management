package com.railway.blockoptimizer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

public class TrainScheduleDTO {
    private UUID id;

    @NotNull(message = "Train ID is required")
    private UUID trainId;
    private String trainNumber;
    private String trainName;

    @NotNull(message = "Corridor ID is required")
    private UUID corridorId;
    private String corridorCode;

    @NotNull(message = "Arrival time is required")
    private OffsetDateTime arrivalTime;

    @NotNull(message = "Departure time is required")
    private OffsetDateTime departureTime;

    @NotNull(message = "Start KM is required")
    private Double startKm;

    @NotNull(message = "End KM is required")
    private Double endKm;

    @NotBlank(message = "Day of week is required")
    private String dayOfWeek;

    private String frequencyPattern;

    public TrainScheduleDTO() {}

    public TrainScheduleDTO(UUID id, UUID trainId, String trainNumber, String trainName, UUID corridorId, String corridorCode, OffsetDateTime arrivalTime, OffsetDateTime departureTime, Double startKm, Double endKm, String dayOfWeek, String frequencyPattern) {
        this.id = id;
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.corridorId = corridorId;
        this.corridorCode = corridorCode;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.startKm = startKm;
        this.endKm = endKm;
        this.dayOfWeek = dayOfWeek;
        this.frequencyPattern = frequencyPattern;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UUID id;
        private UUID trainId;
        private String trainNumber;
        private String trainName;
        private UUID corridorId;
        private String corridorCode;
        private OffsetDateTime arrivalTime;
        private OffsetDateTime departureTime;
        private Double startKm;
        private Double endKm;
        private String dayOfWeek;
        private String frequencyPattern;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder trainId(UUID trainId) { this.trainId = trainId; return this; }
        public Builder trainNumber(String trainNumber) { this.trainNumber = trainNumber; return this; }
        public Builder trainName(String trainName) { this.trainName = trainName; return this; }
        public Builder corridorId(UUID corridorId) { this.corridorId = corridorId; return this; }
        public Builder corridorCode(String corridorCode) { this.corridorCode = corridorCode; return this; }
        public Builder arrivalTime(OffsetDateTime arrivalTime) { this.arrivalTime = arrivalTime; return this; }
        public Builder departureTime(OffsetDateTime departureTime) { this.departureTime = departureTime; return this; }
        public Builder startKm(Double startKm) { this.startKm = startKm; return this; }
        public Builder endKm(Double endKm) { this.endKm = endKm; return this; }
        public Builder dayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; return this; }
        public Builder frequencyPattern(String frequencyPattern) { this.frequencyPattern = frequencyPattern; return this; }

        public TrainScheduleDTO build() {
            return new TrainScheduleDTO(id, trainId, trainNumber, trainName, corridorId, corridorCode, arrivalTime, departureTime, startKm, endKm, dayOfWeek, frequencyPattern);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getTrainId() { return trainId; }
    public void setTrainId(UUID trainId) { this.trainId = trainId; }

    public String getTrainNumber() { return trainNumber; }
    public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }

    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

    public UUID getCorridorId() { return corridorId; }
    public void setCorridorId(UUID corridorId) { this.corridorId = corridorId; }

    public String getCorridorCode() { return corridorCode; }
    public void setCorridorCode(String corridorCode) { this.corridorCode = corridorCode; }

    public OffsetDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(OffsetDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public OffsetDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(OffsetDateTime departureTime) { this.departureTime = departureTime; }

    public Double getStartKm() { return startKm; }
    public void setStartKm(Double startKm) { this.startKm = startKm; }

    public Double getEndKm() { return endKm; }
    public void setEndKm(Double endKm) { this.endKm = endKm; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getFrequencyPattern() { return frequencyPattern; }
    public void setFrequencyPattern(String frequencyPattern) { this.frequencyPattern = frequencyPattern; }
}
