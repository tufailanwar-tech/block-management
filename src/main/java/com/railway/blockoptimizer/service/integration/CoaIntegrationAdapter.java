package com.railway.blockoptimizer.service.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CoaIntegrationAdapter {
    private static final Logger log = LoggerFactory.getLogger(CoaIntegrationAdapter.class);

    public int syncControlOfficeMovements() {
        log.info("COA Adapter: Synchronizing Control Office live movement logs...");
        return 20;
    }
}
