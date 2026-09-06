package com.railway.blockoptimizer.repository;

import com.railway.blockoptimizer.domain.entity.Corridor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CorridorRepository extends JpaRepository<Corridor, UUID> {
    Optional<Corridor> findByCode(String code);
}
