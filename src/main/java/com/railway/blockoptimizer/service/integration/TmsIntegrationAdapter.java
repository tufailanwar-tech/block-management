package com.railway.blockoptimizer.service.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TmsIntegrationAdapter {
    private static final Logger log = LoggerFactory.getLogger(TmsIntegrationAdapter.class);

    public int syncTrainSchedules() {
        log.info("TMS Adapter: Ingesting active train schedule feeds...");
        return 12;
    }
}
