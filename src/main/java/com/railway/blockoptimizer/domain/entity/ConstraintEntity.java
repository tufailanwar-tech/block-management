package com.railway.blockoptimizer.domain.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "constraints")
public class ConstraintEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "constraint_type", nullable = false, length = 100)
    private String constraintType;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String parameters;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public ConstraintEntity() {}

    public ConstraintEntity(UUID id, String constraintType, String description, String parameters, Boolean isActive, OffsetDateTime createdAt) {
        this.id = id;
        this.constraintType = constraintType;
        this.description = description;
        this.parameters = parameters;
        this.isActive = isActive != null ? isActive : true;
        this.createdAt = createdAt != null ? createdAt : OffsetDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String constraintType;
        private String description;
        private String parameters;
        private Boolean isActive = true;
        private OffsetDateTime createdAt;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder constraintType(String constraintType) { this.constraintType = constraintType; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder parameters(String parameters) { this.parameters = parameters; return this; }
        public Builder isActive(Boolean isActive) { this.isActive = isActive; return this; }
        public Builder createdAt(OffsetDateTime createdAt) { this.createdAt = createdAt; return this; }

        public ConstraintEntity build() {
            return new ConstraintEntity(id, constraintType, description, parameters, isActive, createdAt);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getConstraintType() { return constraintType; }
    public void setConstraintType(String constraintType) { this.constraintType = constraintType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getParameters() { return parameters; }
    public void setParameters(String parameters) { this.parameters = parameters; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
