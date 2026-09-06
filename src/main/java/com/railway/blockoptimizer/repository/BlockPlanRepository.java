package com.railway.blockoptimizer.repository;

import com.railway.blockoptimizer.domain.entity.BlockPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface BlockPlanRepository extends JpaRepository<BlockPlan, UUID> {
    List<BlockPlan> findByHorizonType(String horizonType);
}
