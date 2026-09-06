package com.railway.blockoptimizer.service.integration;

import com.railway.blockoptimizer.domain.entity.IntegrationSyncLog;
import com.railway.blockoptimizer.repository.IntegrationSyncLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExternalIntegrationService {

    private static final Logger log = LoggerFactory.getLogger(ExternalIntegrationService.class);

    private final IntegrationSyncLogRepository syncLogRepository;
    private final TmsIntegrationAdapter tmsAdapter;
    private final SmmsIntegrationAdapter smmsAdapter;
    private final TdmsIntegrationAdapter tdmsAdapter;
    private final CoaIntegrationAdapter coaAdapter;

    public ExternalIntegrationService(IntegrationSyncLogRepository syncLogRepository, TmsIntegrationAdapter tmsAdapter, SmmsIntegrationAdapter smmsAdapter, TdmsIntegrationAdapter tdmsAdapter, CoaIntegrationAdapter coaAdapter) {
        this.syncLogRepository = syncLogRepository;
        this.tmsAdapter = tmsAdapter;
        this.smmsAdapter = smmsAdapter;
        this.tdmsAdapter = tdmsAdapter;
        this.coaAdapter = coaAdapter;
    }

    public Map<String, Object> syncSystemData(String systemName) {
        log.info("Triggering data synchronization for external system: {}", systemName);
        int count = 0;
        String status = "SUCCESS";
        String details = "Synchronization completed successfully";

        try {
            switch (systemName.toUpperCase()) {
                case "TMS" -> count = tmsAdapter.syncTrainSchedules();
                case "SMMS" -> count = smmsAdapter.syncMaintenanceTasks();
                case "TDMS" -> count = tdmsAdapter.syncDispatchFeeds();
                case "COA" -> count = coaAdapter.syncControlOfficeMovements();
                default -> throw new IllegalArgumentException("Unknown integration system: " + systemName);
            }
        } catch (Exception e) {
            status = "FAILED";
            details = "Error: " + e.getMessage();
            log.error("Failed integration sync for system {}", systemName, e);
        }

        IntegrationSyncLog syncLog = IntegrationSyncLog.builder()
                .systemName(systemName.toUpperCase())
                .syncType("MANUAL_TRIGGER")
                .recordsProcessed(count)
                .status(status)
                .errorDetails(details)
                .build();
        syncLogRepository.save(syncLog);

        Map<String, Object> result = new HashMap<>();
        result.put("systemName", systemName.toUpperCase());
        result.put("recordsProcessed", count);
        result.put("status", status);
        result.put("message", details);
        return result;
    }
}
