package com.railway.blockoptimizer.domain.entity;

import com.railway.blockoptimizer.domain.enums.SourceSystem;
import com.railway.blockoptimizer.domain.enums.TrainType;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "train_number", nullable = false, unique = true, length = 50)
    private String trainNumber;

    @Column(name = "train_name", nullable = false)
    private String trainName;

    @Enumerated(EnumType.STRING)
    @Column(name = "train_type", nullable = false, length = 50)
    private TrainType trainType;

    @Column(name = "priority_level", nullable = false)
    private Integer priorityLevel;

    @Column(name = "origin_station", nullable = false, length = 100)
    private String originStation;

    @Column(name = "destination_station", nullable = false, length = 100)
    private String destinationStation;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_system", nullable = false, length = 50)
    private SourceSystem sourceSystem;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Train() {}

    public Train(UUID id, String trainNumber, String trainName, TrainType trainType, Integer priorityLevel, String originStation, String destinationStation, SourceSystem sourceSystem, OffsetDateTime createdAt) {
        this.id = id;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.trainType = trainType;
        this.priorityLevel = priorityLevel;
        this.originStation = originStation;
        this.destinationStation = destinationStation;
        this.sourceSystem = sourceSystem;
        this.createdAt = createdAt != null ? createdAt : OffsetDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String trainNumber;
        private String trainName;
        private TrainType trainType;
        private Integer priorityLevel;
        private String originStation;
        private String destinationStation;
        private SourceSystem sourceSystem;
        private OffsetDateTime createdAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder trainNumber(String trainNumber) { this.trainNumber = trainNumber; return this; }
        public Builder trainName(String trainName) { this.trainName = trainName; return this; }
        public Builder trainType(TrainType trainType) { this.trainType = trainType; return this; }
        public Builder priorityLevel(Integer priorityLevel) { this.priorityLevel = priorityLevel; return this; }
        public Builder originStation(String originStation) { this.originStation = originStation; return this; }
        public Builder destinationStation(String destinationStation) { this.destinationStation = destinationStation; return this; }
        public Builder sourceSystem(SourceSystem sourceSystem) { this.sourceSystem = sourceSystem; return this; }
        public Builder createdAt(OffsetDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Train build() {
            return new Train(id, trainNumber, trainName, trainType, priorityLevel, originStation, destinationStation, sourceSystem, createdAt);
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

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
