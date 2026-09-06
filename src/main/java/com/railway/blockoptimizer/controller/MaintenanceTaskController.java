package com.railway.blockoptimizer.controller;

import com.railway.blockoptimizer.dto.MaintenanceTaskRequestDTO;
import com.railway.blockoptimizer.dto.MaintenanceTaskResponseDTO;
import com.railway.blockoptimizer.service.MaintenanceTaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceTaskController {

    private final MaintenanceTaskService maintenanceTaskService;

    public MaintenanceTaskController(MaintenanceTaskService maintenanceTaskService) {
        this.maintenanceTaskService = maintenanceTaskService;
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceTaskResponseDTO>> getAllTasks() {
        return ResponseEntity.ok(maintenanceTaskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceTaskResponseDTO> getTaskById(@PathVariable UUID id) {
        return ResponseEntity.ok(maintenanceTaskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<MaintenanceTaskResponseDTO> createTask(@Valid @RequestBody MaintenanceTaskRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceTaskService.createTask(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceTaskResponseDTO> updateTask(@PathVariable UUID id, @Valid @RequestBody MaintenanceTaskRequestDTO dto) {
        return ResponseEntity.ok(maintenanceTaskService.updateTask(id, dto));
    }
}
