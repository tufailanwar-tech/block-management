package com.railway.blockoptimizer.repository;

import com.railway.blockoptimizer.domain.entity.MaintenanceTask;
import com.railway.blockoptimizer.domain.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface MaintenanceTaskRepository extends JpaRepository<MaintenanceTask, UUID> {
    List<MaintenanceTask> findByDepartmentId(UUID departmentId);
    List<MaintenanceTask> findByStatus(TaskStatus status);
}
