package com.railway.blockoptimizer.domain.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "optimization_runs")
public class OptimizationRun {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "run_number", nullable = false, unique = true, length = 50)
    private String runNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_plan_id")
    private BlockPlan blockPlan;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String parameters;

    @Column(name = "started_at")
    private OffsetDateTime startedAt;

    @Column(name = "completed_at")
    private OffsetDateTime completedAt;

    public OptimizationRun() {}

    public OptimizationRun(UUID id, String runNumber, BlockPlan blockPlan, String status, String parameters, OffsetDateTime startedAt, OffsetDateTime completedAt) {
        this.id = id;
        this.runNumber = runNumber;
        this.blockPlan = blockPlan;
        this.status = status;
        this.parameters = parameters;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UUID id;
        private String runNumber;
        private BlockPlan blockPlan;
        private String status;
        private String parameters;
        private OffsetDateTime startedAt;
        private OffsetDateTime completedAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder runNumber(String runNumber) { this.runNumber = runNumber; return this; }
        public Builder blockPlan(BlockPlan blockPlan) { this.blockPlan = blockPlan; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder parameters(String parameters) { this.parameters = parameters; return this; }
        public Builder startedAt(OffsetDateTime startedAt) { this.startedAt = startedAt; return this; }
        public Builder completedAt(OffsetDateTime completedAt) { this.completedAt = completedAt; return this; }

        public OptimizationRun build() {
            return new OptimizationRun(id, runNumber, blockPlan, status, parameters, startedAt, completedAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getRunNumber() { return runNumber; }
    public void setRunNumber(String runNumber) { this.runNumber = runNumber; }

    public BlockPlan getBlockPlan() { return blockPlan; }
    public void setBlockPlan(BlockPlan blockPlan) { this.blockPlan = blockPlan; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getParameters() { return parameters; }
    public void setParameters(String parameters) { this.parameters = parameters; }

    public OffsetDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(OffsetDateTime startedAt) { this.startedAt = startedAt; }

    public OffsetDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(OffsetDateTime completedAt) { this.completedAt = completedAt; }
}
