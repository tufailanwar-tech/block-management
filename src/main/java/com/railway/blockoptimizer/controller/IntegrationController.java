package com.railway.blockoptimizer.controller;

import com.railway.blockoptimizer.service.integration.ExternalIntegrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/integrations")
public class IntegrationController {

    private final ExternalIntegrationService externalIntegrationService;

    public IntegrationController(ExternalIntegrationService externalIntegrationService) {
        this.externalIntegrationService = externalIntegrationService;
    }

    @PostMapping("/sync/{system}")
    public ResponseEntity<Map<String, Object>> syncSystemData(@PathVariable String system) {
        return ResponseEntity.ok(externalIntegrationService.syncSystemData(system));
    }
}
