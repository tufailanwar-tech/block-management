package com.railway.blockoptimizer.controller;

import com.railway.blockoptimizer.dto.ConflictDTO;
import com.railway.blockoptimizer.service.ConflictDetectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/conflicts")
public class ConflictController {

    private final ConflictDetectionService conflictDetectionService;

    public ConflictController(ConflictDetectionService conflictDetectionService) {
        this.conflictDetectionService = conflictDetectionService;
    }

    @GetMapping
    public ResponseEntity<List<ConflictDTO>> getAllConflicts() {
        return ResponseEntity.ok(conflictDetectionService.getAllConflicts());
    }
}
