package com.railway.blockoptimizer.domain.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "train_schedules")
public class TrainSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corridor_id", nullable = false)
    private Corridor corridor;

    @Column(name = "arrival_time", nullable = false)
    private OffsetDateTime arrivalTime;

    @Column(name = "departure_time", nullable = false)
    private OffsetDateTime departureTime;

    @Column(name = "start_km", nullable = false)
    private Double startKm;

    @Column(name = "end_km", nullable = false)
    private Double endKm;

    @Column(name = "day_of_week", nullable = false, length = 20)
    private String dayOfWeek;

    @Column(name = "frequency_pattern", length = 100)
    private String frequencyPattern;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public TrainSchedule() {}

    public TrainSchedule(UUID id, Train train, Corridor corridor, OffsetDateTime arrivalTime, OffsetDateTime departureTime, Double startKm, Double endKm, String dayOfWeek, String frequencyPattern, OffsetDateTime createdAt) {
        this.id = id;
        this.train = train;
        this.corridor = corridor;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.startKm = startKm;
        this.endKm = endKm;
        this.dayOfWeek = dayOfWeek;
        this.frequencyPattern = frequencyPattern;
        this.createdAt = createdAt != null ? createdAt : OffsetDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private Train train;
        private Corridor corridor;
        private OffsetDateTime arrivalTime;
        private OffsetDateTime departureTime;
        private Double startKm;
        private Double endKm;
        private String dayOfWeek;
        private String frequencyPattern;
        private OffsetDateTime createdAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder train(Train train) { this.train = train; return this; }
        public Builder corridor(Corridor corridor) { this.corridor = corridor; return this; }
        public Builder arrivalTime(OffsetDateTime arrivalTime) { this.arrivalTime = arrivalTime; return this; }
        public Builder departureTime(OffsetDateTime departureTime) { this.departureTime = departureTime; return this; }
        public Builder startKm(Double startKm) { this.startKm = startKm; return this; }
        public Builder endKm(Double endKm) { this.endKm = endKm; return this; }
        public Builder dayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; return this; }
        public Builder frequencyPattern(String frequencyPattern) { this.frequencyPattern = frequencyPattern; return this; }
        public Builder createdAt(OffsetDateTime createdAt) { this.createdAt = createdAt; return this; }

        public TrainSchedule build() {
            return new TrainSchedule(id, train, corridor, arrivalTime, departureTime, startKm, endKm, dayOfWeek, frequencyPattern, createdAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Train getTrain() { return train; }
    public void setTrain(Train train) { this.train = train; }

    public Corridor getCorridor() { return corridor; }
    public void setCorridor(Corridor corridor) { this.corridor = corridor; }

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

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
