package com.railway.blockoptimizer.dto;

import com.railway.blockoptimizer.domain.enums.SourceSystem;
import com.railway.blockoptimizer.domain.enums.TrainType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class TrainDTO {
    private UUID id;

    @NotBlank(message = "Train number is required")
    private String trainNumber;

    @NotBlank(message = "Train name is required")
    private String trainName;

    @NotNull(message = "Train type is required")
    private TrainType trainType;

    @NotNull(message = "Priority level is required")
    @Min(value = 1, message = "Priority must be 1 to 5")
    @Max(value = 5, message = "Priority must be 1 to 5")
    private Integer priorityLevel;

    @NotBlank(message = "Origin station is required")
    private String originStation;

    @NotBlank(message = "Destination station is required")
    private String destinationStation;

    private SourceSystem sourceSystem;

    public TrainDTO() {}

    public TrainDTO(UUID id, String trainNumber, String trainName, TrainType trainType, Integer priorityLevel, String originStation, String destinationStation, SourceSystem sourceSystem) {
        this.id = id;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.trainType = trainType;
        this.priorityLevel = priorityLevel;
        this.originStation = originStation;
        this.destinationStation = destinationStation;
        this.sourceSystem = sourceSystem;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UUID id;
        private String trainNumber;
        private String trainName;
        private TrainType trainType;
        private Integer priorityLevel;
        private String originStation;
        private String destinationStation;
        private SourceSystem sourceSystem;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder trainNumber(String trainNumber) { this.trainNumber = trainNumber; return this; }
        public Builder trainName(String trainName) { this.trainName = trainName; return this; }
        public Builder trainType(TrainType trainType) { this.trainType = trainType; return this; }
        public Builder priorityLevel(Integer priorityLevel) { this.priorityLevel = priorityLevel; return this; }
        public Builder originStation(String originStation) { this.originStation = originStation; return this; }
        public Builder destinationStation(String destinationStation) { this.destinationStation = destinationStation; return this; }
        public Builder sourceSystem(SourceSystem sourceSystem) { this.sourceSystem = sourceSystem; return this; }

        public TrainDTO build() {
            return new TrainDTO(id, trainNumber, trainName, trainType, priorityLevel, originStation, destinationStation, sourceSystem);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTrainNumber() { return trainNumber; }
    public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }

    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

    public TrainType getTrainType() { return trainType; }
    public void setTrainType(TrainType trainType) { this.trainType = trainType; }

    public Integer getPriorityLevel() { return priorityLevel; }
    public void setPriorityLevel(Integer priorityLevel) { this.priorityLevel = priorityLevel; }

    public String getOriginStation() { return originStation; }
    public void setOriginStation(String originStation) { this.originStation = originStation; }

    public String getDestinationStation() { return destinationStation; }
    public void setDestinationStation(String destinationStation) { this.destinationStation = destinationStation; }

    public SourceSystem getSourceSystem() { return sourceSystem; }
    public void setSourceSystem(SourceSystem sourceSystem) { this.sourceSystem = sourceSystem; }
}
