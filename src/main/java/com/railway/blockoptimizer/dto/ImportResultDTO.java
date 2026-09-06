package com.railway.blockoptimizer.dto;

import java.util.ArrayList;
import java.util.List;

public class ImportResultDTO {
    private String entityType;
    private int totalRecords;
    private int successCount;
    private int failureCount;
    private List<String> errors = new ArrayList<>();
    private List<String> importedKeys = new ArrayList<>();

    public ImportResultDTO() {}

    public ImportResultDTO(String entityType, int totalRecords, int successCount, int failureCount, List<String> errors, List<String> importedKeys) {
        this.entityType = entityType;
        this.totalRecords = totalRecords;
        this.successCount = successCount;
        this.failureCount = failureCount;
        this.errors = errors != null ? errors : new ArrayList<>();
        this.importedKeys = importedKeys != null ? importedKeys : new ArrayList<>();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String entityType;
        private int totalRecords;
        private int successCount;
        private int failureCount;
        private List<String> errors = new ArrayList<>();
        private List<String> importedKeys = new ArrayList<>();

        public Builder entityType(String entityType) { this.entityType = entityType; return this; }
        public Builder totalRecords(int totalRecords) { this.totalRecords = totalRecords; return this; }
        public Builder successCount(int successCount) { this.successCount = successCount; return this; }
        public Builder failureCount(int failureCount) { this.failureCount = failureCount; return this; }
        public Builder errors(List<String> errors) { this.errors = errors; return this; }
        public Builder importedKeys(List<String> importedKeys) { this.importedKeys = importedKeys; return this; }

        public ImportResultDTO build() {
            return new ImportResultDTO(entityType, totalRecords, successCount, failureCount, errors, importedKeys);
        }
    }

    public String getEntityType() { return entityType; }
    public void setEntityType(String entityType) { this.entityType = entityType; }

    public int getTotalRecords() { return totalRecords; }
    public void setTotalRecords(int totalRecords) { this.totalRecords = totalRecords; }

    public int getSuccessCount() { return successCount; }
    public void setSuccessCount(int successCount) { this.successCount = successCount; }

    public int getFailureCount() { return failureCount; }
    public void setFailureCount(int failureCount) { this.failureCount = failureCount; }

    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }

    public List<String> getImportedKeys() { return importedKeys; }
    public void setImportedKeys(List<String> importedKeys) { this.importedKeys = importedKeys; }
}
