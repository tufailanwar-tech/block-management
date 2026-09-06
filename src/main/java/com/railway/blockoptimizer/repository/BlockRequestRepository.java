package com.railway.blockoptimizer.repository;

import com.railway.blockoptimizer.domain.entity.BlockRequest;
import com.railway.blockoptimizer.domain.enums.BlockRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlockRequestRepository extends JpaRepository<BlockRequest, UUID> {
    Optional<BlockRequest> findByRequestNumber(String requestNumber);
    List<BlockRequest> findByDepartmentId(UUID departmentId);
    List<BlockRequest> findByStatus(BlockRequestStatus status);

    @Query("SELECT br FROM BlockRequest br WHERE br.corridor.id = :corridorId AND br.id <> :excludeId AND br.startTime <= :endTime AND br.endTime >= :startTime")
    List<BlockRequest> findOverlappingRequests(@Param("corridorId") UUID corridorId,
                                                @Param("excludeId") UUID excludeId,
                                                @Param("startTime") OffsetDateTime startTime,
                                                @Param("endTime") OffsetDateTime endTime);
}
