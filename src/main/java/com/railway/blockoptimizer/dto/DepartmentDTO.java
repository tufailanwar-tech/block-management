package com.railway.blockoptimizer.dto;

import java.util.UUID;

public class DepartmentDTO {
    private UUID id;
    private String code;
    private String name;
    private String description;

    public DepartmentDTO() {}

    public DepartmentDTO(UUID id, String code, String name, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UUID id;
        private String code;
        private String name;
        private String description;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder code(String code) { this.code = code; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder description(String description) { this.description = description; return this; }

        public DepartmentDTO build() {
            return new DepartmentDTO(id, code, name, description);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
