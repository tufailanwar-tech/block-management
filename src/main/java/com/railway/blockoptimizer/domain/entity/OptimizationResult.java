package com.railway.blockoptimizer.domain.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "optimization_results")
public class OptimizationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "optimization_run_id", nullable = false)
    private OptimizationRun optimizationRun;

    @Column(name = "total_blocks_scheduled", nullable = false)
    private Integer totalBlocksScheduled;

    @Column(name = "conflicts_resolved", nullable = false)
    private Integer conflictsResolved;

    @Column(nullable = false)
    private Double score;

    @Column(columnDefinition = "TEXT")
    private String metrics;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public OptimizationResult() {}

    public OptimizationResult(UUID id, OptimizationRun optimizationRun, Integer totalBlocksScheduled, Integer conflictsResolved, Double score, String metrics, OffsetDateTime createdAt) {
        this.id = id;
        this.optimizationRun = optimizationRun;
        this.totalBlocksScheduled = totalBlocksScheduled;
        this.conflictsResolved = conflictsResolved;
        this.score = score;
        this.metrics = metrics;
        this.createdAt = createdAt != null ? createdAt : OffsetDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UUID id;
        private OptimizationRun optimizationRun;
        private Integer totalBlocksScheduled;
        private Integer conflictsResolved;
        private Double score;
        private String metrics;
        private OffsetDateTime createdAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder optimizationRun(OptimizationRun optimizationRun) { this.optimizationRun = optimizationRun; return this; }
        public Builder totalBlocksScheduled(Integer totalBlocksScheduled) { this.totalBlocksScheduled = totalBlocksScheduled; return this; }
        public Builder conflictsResolved(Integer conflictsResolved) { this.conflictsResolved = conflictsResolved; return this; }
        public Builder score(Double score) { this.score = score; return this; }
        public Builder metrics(String metrics) { this.metrics = metrics; return this; }
        public Builder createdAt(OffsetDateTime createdAt) { this.createdAt = createdAt; return this; }

        public OptimizationResult build() {
            return new OptimizationResult(id, optimizationRun, totalBlocksScheduled, conflictsResolved, score, metrics, createdAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public OptimizationRun getOptimizationRun() { return optimizationRun; }
    public void setOptimizationRun(OptimizationRun optimizationRun) { this.optimizationRun = optimizationRun; }

    public Integer getTotalBlocksScheduled() { return totalBlocksScheduled; }
    public void setTotalBlocksScheduled(Integer totalBlocksScheduled) { this.totalBlocksScheduled = totalBlocksScheduled; }

    public Integer getConflictsResolved() { return conflictsResolved; }
    public void setConflictsResolved(Integer conflictsResolved) { this.conflictsResolved = conflictsResolved; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public String getMetrics() { return metrics; }
    public void setMetrics(String metrics) { this.metrics = metrics; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
