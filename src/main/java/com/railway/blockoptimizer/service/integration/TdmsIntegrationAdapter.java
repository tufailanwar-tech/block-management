package com.railway.blockoptimizer.service.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TdmsIntegrationAdapter {
    private static final Logger log = LoggerFactory.getLogger(TdmsIntegrationAdapter.class);

    public int syncDispatchFeeds() {
        log.info("TDMS Adapter: Synchronizing train dispatch logs...");
        return 15;
    }
}
