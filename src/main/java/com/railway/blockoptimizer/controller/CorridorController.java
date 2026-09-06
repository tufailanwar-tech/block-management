package com.railway.blockoptimizer.controller;

import com.railway.blockoptimizer.dto.CorridorDTO;
import com.railway.blockoptimizer.service.CorridorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/corridors")
public class CorridorController {

    private final CorridorService corridorService;

    public CorridorController(CorridorService corridorService) {
        this.corridorService = corridorService;
    }

    @GetMapping
    public ResponseEntity<List<CorridorDTO>> getAllCorridors() {
        return ResponseEntity.ok(corridorService.getAllCorridors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorridorDTO> getCorridorById(@PathVariable UUID id) {
        return ResponseEntity.ok(corridorService.getCorridorById(id));
    }

    @PostMapping
    public ResponseEntity<CorridorDTO> createCorridor(@Valid @RequestBody CorridorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(corridorService.createCorridor(dto));
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<Map<String, Object>> getAvailability(@PathVariable UUID id) {
        return ResponseEntity.ok(corridorService.getCorridorAvailability(id));
    }
}
