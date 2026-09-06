package com.railway.blockoptimizer.controller;

import com.railway.blockoptimizer.domain.enums.BlockRequestStatus;
import com.railway.blockoptimizer.dto.BlockRequestCreateDTO;
import com.railway.blockoptimizer.dto.BlockRequestResponseDTO;
import com.railway.blockoptimizer.service.BlockRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/block-requests")
public class BlockRequestController {

    private final BlockRequestService blockRequestService;

    public BlockRequestController(BlockRequestService blockRequestService) {
        this.blockRequestService = blockRequestService;
    }

    @GetMapping
    public ResponseEntity<List<BlockRequestResponseDTO>> getAllBlockRequests() {
        return ResponseEntity.ok(blockRequestService.getAllBlockRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlockRequestResponseDTO> getBlockRequestById(@PathVariable UUID id) {
        return ResponseEntity.ok(blockRequestService.getBlockRequestById(id));
    }

    @PostMapping
    public ResponseEntity<BlockRequestResponseDTO> createBlockRequest(@Valid @RequestBody BlockRequestCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blockRequestService.createBlockRequest(dto));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BlockRequestResponseDTO> updateStatus(
            @PathVariable UUID id,
            @RequestParam BlockRequestStatus status,
            @RequestParam(required = false) String remarks) {
        return ResponseEntity.ok(blockRequestService.updateStatus(id, status, remarks));
    }
}
