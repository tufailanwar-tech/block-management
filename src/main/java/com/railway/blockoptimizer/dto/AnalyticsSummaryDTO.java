package com.railway.blockoptimizer.dto;

import java.util.Map;

public class AnalyticsSummaryDTO {
    private long totalDepartments;
    private long totalCorridors;
    private long totalAssets;
    private long totalMaintenanceTasks;
    private long totalTrains;
    private long totalSchedules;
    private long totalBlockRequests;
    private long pendingBlockRequests;
    private long approvedBlockRequests;
    private long totalConflicts;
    private Map<String, Long> tasksByPriority;
    private Map<String, Long> requestsByBlockType;
    private Map<String, Long> conflictsBySeverity;

    public AnalyticsSummaryDTO() {}

    public AnalyticsSummaryDTO(long totalDepartments, long totalCorridors, long totalAssets, long totalMaintenanceTasks, long totalTrains, long totalSchedules, long totalBlockRequests, long pendingBlockRequests, long approvedBlockRequests, long totalConflicts, Map<String, Long> tasksByPriority, Map<String, Long> requestsByBlockType, Map<String, Long> conflictsBySeverity) {
        this.totalDepartments = totalDepartments;
        this.totalCorridors = totalCorridors;
        this.totalAssets = totalAssets;
        this.totalMaintenanceTasks = totalMaintenanceTasks;
        this.totalTrains = totalTrains;
        this.totalSchedules = totalSchedules;
        this.totalBlockRequests = totalBlockRequests;
        this.pendingBlockRequests = pendingBlockRequests;
        this.approvedBlockRequests = approvedBlockRequests;
        this.totalConflicts = totalConflicts;
        this.tasksByPriority = tasksByPriority;
        this.requestsByBlockType = requestsByBlockType;
        this.conflictsBySeverity = conflictsBySeverity;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private long totalDepartments;
        private long totalCorridors;
        private long totalAssets;
        private long totalMaintenanceTasks;
        private long totalTrains;
        private long totalSchedules;
        private long totalBlockRequests;
        private long pendingBlockRequests;
        private long approvedBlockRequests;
        private long totalConflicts;
        private Map<String, Long> tasksByPriority;
        private Map<String, Long> requestsByBlockType;
        private Map<String, Long> conflictsBySeverity;

        public Builder totalDepartments(long totalDepartments) { this.totalDepartments = totalDepartments; return this; }
        public Builder totalCorridors(long totalCorridors) { this.totalCorridors = totalCorridors; return this; }
        public Builder totalAssets(long totalAssets) { this.totalAssets = totalAssets; return this; }
        public Builder totalMaintenanceTasks(long totalMaintenanceTasks) { this.totalMaintenanceTasks = totalMaintenanceTasks; return this; }
        public Builder totalTrains(long totalTrains) { this.totalTrains = totalTrains; return this; }
        public Builder totalSchedules(long totalSchedules) { this.totalSchedules = totalSchedules; return this; }
        public Builder totalBlockRequests(long totalBlockRequests) { this.totalBlockRequests = totalBlockRequests; return this; }
        public Builder pendingBlockRequests(long pendingBlockRequests) { this.pendingBlockRequests = pendingBlockRequests; return this; }
        public Builder approvedBlockRequests(long approvedBlockRequests) { this.approvedBlockRequests = approvedBlockRequests; return this; }
        public Builder totalConflicts(long totalConflicts) { this.totalConflicts = totalConflicts; return this; }
        public Builder tasksByPriority(Map<String, Long> tasksByPriority) { this.tasksByPriority = tasksByPriority; return this; }
        public Builder requestsByBlockType(Map<String, Long> requestsByBlockType) { this.requestsByBlockType = requestsByBlockType; return this; }
        public Builder conflictsBySeverity(Map<String, Long> conflictsBySeverity) { this.conflictsBySeverity = conflictsBySeverity; return this; }

        public AnalyticsSummaryDTO build() {
            return new AnalyticsSummaryDTO(totalDepartments, totalCorridors, totalAssets, totalMaintenanceTasks, totalTrains, totalSchedules, totalBlockRequests, pendingBlockRequests, approvedBlockRequests, totalConflicts, tasksByPriority, requestsByBlockType, conflictsBySeverity);
        }
    }

    public long getTotalDepartments() { return totalDepartments; }
    public void setTotalDepartments(long totalDepartments) { this.totalDepartments = totalDepartments; }

    public long getTotalCorridors() { return totalCorridors; }
    public void setTotalCorridors(long totalCorridors) { this.totalCorridors = totalCorridors; }

    public long getTotalAssets() { return totalAssets; }
    public void setTotalAssets(long totalAssets) { this.totalAssets = totalAssets; }

    public long getTotalMaintenanceTasks() { return totalMaintenanceTasks; }
    public void setTotalMaintenanceTasks(long totalMaintenanceTasks) { this.totalMaintenanceTasks = totalMaintenanceTasks; }

    public long getTotalTrains() { return totalTrains; }
    public void setTotalTrains(long totalTrains) { this.totalTrains = totalTrains; }

    public long getTotalSchedules() { return totalSchedules; }
    public void setTotalSchedules(long totalSchedules) { this.totalSchedules = totalSchedules; }

    public long getTotalBlockRequests() { return totalBlockRequests; }
    public void setTotalBlockRequests(long totalBlockRequests) { this.totalBlockRequests = totalBlockRequests; }

    public long getPendingBlockRequests() { return pendingBlockRequests; }
    public void setPendingBlockRequests(long pendingBlockRequests) { this.pendingBlockRequests = pendingBlockRequests; }

    public long getApprovedBlockRequests() { return approvedBlockRequests; }
    public void setApprovedBlockRequests(long approvedBlockRequests) { this.approvedBlockRequests = approvedBlockRequests; }

    public long getTotalConflicts() { return totalConflicts; }
    public void setTotalConflicts(long totalConflicts) { this.totalConflicts = totalConflicts; }

    public Map<String, Long> getTasksByPriority() { return tasksByPriority; }
    public void setTasksByPriority(Map<String, Long> tasksByPriority) { this.tasksByPriority = tasksByPriority; }

    public Map<String, Long> getRequestsByBlockType() { return requestsByBlockType; }
    public void setRequestsByBlockType(Map<String, Long> requestsByBlockType) { this.requestsByBlockType = requestsByBlockType; }

    public Map<String, Long> getConflictsBySeverity() { return conflictsBySeverity; }
    public void setConflictsBySeverity(Map<String, Long> conflictsBySeverity) { this.conflictsBySeverity = conflictsBySeverity; }
}
