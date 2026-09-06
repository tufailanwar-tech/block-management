package com.railway.blockoptimizer.service;

import com.railway.blockoptimizer.domain.entity.Asset;
import com.railway.blockoptimizer.domain.entity.Corridor;
import com.railway.blockoptimizer.dto.AssetDTO;
import com.railway.blockoptimizer.exception.ResourceNotFoundException;
import com.railway.blockoptimizer.repository.AssetRepository;
import com.railway.blockoptimizer.repository.CorridorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AssetService {

    private final AssetRepository assetRepository;
    private final CorridorRepository corridorRepository;

    public AssetService(AssetRepository assetRepository, CorridorRepository corridorRepository) {
        this.assetRepository = assetRepository;
        this.corridorRepository = corridorRepository;
    }

    public List<AssetDTO> getAllAssets() {
        return assetRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AssetDTO> getAssetsByCorridor(UUID corridorId) {
        return assetRepository.findByCorridorId(corridorId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AssetDTO createAsset(AssetDTO dto) {
        Corridor corridor = corridorRepository.findById(dto.getCorridorId())
                .orElseThrow(() -> new ResourceNotFoundException("Corridor not found with ID: " + dto.getCorridorId()));

        Asset asset = Asset.builder()
                .corridor(corridor)
                .assetCode(dto.getAssetCode().toUpperCase().trim())
                .assetType(dto.getAssetType())
                .locationKmStart(dto.getLocationKmStart())
                .locationKmEnd(dto.getLocationKmEnd())
                .status(dto.getStatus())
                .build();

        Asset saved = assetRepository.save(asset);
        return mapToDTO(saved);
    }

    public AssetDTO mapToDTO(Asset asset) {
        return AssetDTO.builder()
                .id(asset.getId())
                .corridorId(asset.getCorridor() != null ? asset.getCorridor().getId() : null)
                .corridorCode(asset.getCorridor() != null ? asset.getCorridor().getCode() : null)
                .assetCode(asset.getAssetCode())
                .assetType(asset.getAssetType())
                .locationKmStart(asset.getLocationKmStart())
                .locationKmEnd(asset.getLocationKmEnd())
                .status(asset.getStatus())
                .build();
    }
}
