package com.railway.blockoptimizer.repository;

import com.railway.blockoptimizer.domain.entity.OptimizationRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OptimizationRunRepository extends JpaRepository<OptimizationRun, UUID> {
    Optional<OptimizationRun> findByRunNumber(String runNumber);
}
