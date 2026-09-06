package com.railway.blockoptimizer.repository;

import com.railway.blockoptimizer.domain.entity.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TrainScheduleRepository extends JpaRepository<TrainSchedule, UUID> {
    List<TrainSchedule> findByTrainId(UUID trainId);
    List<TrainSchedule> findByCorridorId(UUID corridorId);

    @Query("SELECT ts FROM TrainSchedule ts WHERE ts.corridor.id = :corridorId AND ts.arrivalTime <= :endTime AND ts.departureTime >= :startTime")
    List<TrainSchedule> findOverlappingSchedules(@Param("corridorId") UUID corridorId,
                                                 @Param("startTime") OffsetDateTime startTime,
                                                 @Param("endTime") OffsetDateTime endTime);
}
