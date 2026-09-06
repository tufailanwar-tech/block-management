package com.railway.blockoptimizer.controller;

import com.railway.blockoptimizer.dto.AssetDTO;
import com.railway.blockoptimizer.service.AssetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public ResponseEntity<List<AssetDTO>> getAllAssets(@RequestParam(required = false) UUID corridorId) {
        if (corridorId != null) {
            return ResponseEntity.ok(assetService.getAssetsByCorridor(corridorId));
        }
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @PostMapping
    public ResponseEntity<AssetDTO> createAsset(@Valid @RequestBody AssetDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assetService.createAsset(dto));
    }
}
