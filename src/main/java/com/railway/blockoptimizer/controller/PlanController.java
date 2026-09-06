package com.railway.blockoptimizer.controller;

import com.railway.blockoptimizer.dto.BlockPlanDTO;
import com.railway.blockoptimizer.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/weekly")
    public ResponseEntity<List<BlockPlanDTO>> getWeeklyPlans() {
        return ResponseEntity.ok(planService.getPlansByHorizon("WEEKLY"));
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<BlockPlanDTO>> getMonthlyPlans() {
        return ResponseEntity.ok(planService.getPlansByHorizon("MONTHLY"));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<BlockPlanDTO> approvePlan(@PathVariable UUID id) {
        return ResponseEntity.ok(planService.approvePlan(id));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<BlockPlanDTO> rejectPlan(@PathVariable UUID id) {
        return ResponseEntity.ok(planService.rejectPlan(id));
    }
}
