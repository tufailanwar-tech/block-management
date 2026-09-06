package com.railway.blockoptimizer.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class ErrorResponseDTO {
    private int status;
    private String error;
    private String message;
    private List<String> details;
    private String path;
    private OffsetDateTime timestamp = OffsetDateTime.now();

    public ErrorResponseDTO() {}

    public ErrorResponseDTO(int status, String error, String message, List<String> details, String path, OffsetDateTime timestamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.details = details;
        this.path = path;
        this.timestamp = timestamp != null ? timestamp : OffsetDateTime.now();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private int status;
        private String error;
        private String message;
        private List<String> details;
        private String path;
        private OffsetDateTime timestamp;

        public Builder status(int status) { this.status = status; return this; }
        public Builder error(String error) { this.error = error; return this; }
        public Builder message(String message) { this.message = message; return this; }
        public Builder details(List<String> details) { this.details = details; return this; }
        public Builder path(String path) { this.path = path; return this; }
        public Builder timestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; return this; }

        public ErrorResponseDTO build() {
            return new ErrorResponseDTO(status, error, message, details, path, timestamp);
        }
    }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<String> getDetails() { return details; }
    public void setDetails(List<String> details) { this.details = details; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public OffsetDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; }
}
