package com.railway.blockoptimizer.repository;

import com.railway.blockoptimizer.domain.entity.BlockPlanTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface BlockPlanTaskRepository extends JpaRepository<BlockPlanTask, UUID> {
    List<BlockPlanTask> findByBlockPlanId(UUID blockPlanId);
}
