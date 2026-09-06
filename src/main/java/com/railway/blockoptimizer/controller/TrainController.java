package com.railway.blockoptimizer.controller;

import com.railway.blockoptimizer.dto.TrainDTO;
import com.railway.blockoptimizer.dto.TrainScheduleDTO;
import com.railway.blockoptimizer.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("/trains")
    public ResponseEntity<List<TrainDTO>> getAllTrains() {
        return ResponseEntity.ok(trainService.getAllTrains());
    }

    @PostMapping("/trains")
    public ResponseEntity<TrainDTO> createTrain(@Valid @RequestBody TrainDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trainService.createTrain(dto));
    }

    @GetMapping("/train-schedules")
    public ResponseEntity<List<TrainScheduleDTO>> getAllSchedules() {
        return ResponseEntity.ok(trainService.getAllSchedules());
    }

    @PostMapping("/train-schedules")
    public ResponseEntity<TrainScheduleDTO> createSchedule(@Valid @RequestBody TrainScheduleDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trainService.createSchedule(dto));
    }
}
