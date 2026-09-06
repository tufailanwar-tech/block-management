package com.railway.blockoptimizer.repository;

import com.railway.blockoptimizer.domain.entity.OptimizationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OptimizationResultRepository extends JpaRepository<OptimizationResult, UUID> {
    Optional<OptimizationResult> findByOptimizationRunId(UUID optimizationRunId);
}
