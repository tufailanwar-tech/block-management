package com.railway.blockoptimizer.controller;

import com.railway.blockoptimizer.dto.AnalyticsSummaryDTO;
import com.railway.blockoptimizer.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/summary")
    public ResponseEntity<AnalyticsSummaryDTO> getSummary() {
        return ResponseEntity.ok(analyticsService.getSummary());
    }

    @GetMapping("/before-after")
    public ResponseEntity<Map<String, Object>> getBeforeAfter() {
        return ResponseEntity.ok(analyticsService.getBeforeAfterComparison());
    }
}
