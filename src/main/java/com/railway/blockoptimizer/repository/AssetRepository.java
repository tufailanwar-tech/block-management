package com.railway.blockoptimizer.repository;

import com.railway.blockoptimizer.domain.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetRepository extends JpaRepository<Asset, UUID> {
    Optional<Asset> findByAssetCode(String assetCode);
    List<Asset> findByCorridorId(UUID corridorId);
}
