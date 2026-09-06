package com.railway.blockoptimizer.dto;

import java.util.UUID;

public class AuthResponseDTO {
    private String token;
    private String tokenType;
    private UUID userId;
    private String email;
    private String fullName;
    private String role;
    private String departmentCode;

    public AuthResponseDTO() {}

    public AuthResponseDTO(String token, String tokenType, UUID userId, String email, String fullName, String role, String departmentCode) {
        this.token = token;
        this.tokenType = tokenType;
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.departmentCode = departmentCode;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String token;
        private String tokenType;
        private UUID userId;
        private String email;
        private String fullName;
        private String role;
        private String departmentCode;

        public Builder token(String token) { this.token = token; return this; }
        public Builder tokenType(String tokenType) { this.tokenType = tokenType; return this; }
        public Builder userId(UUID userId) { this.userId = userId; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder fullName(String fullName) { this.fullName = fullName; return this; }
        public Builder role(String role) { this.role = role; return this; }
        public Builder departmentCode(String departmentCode) { this.departmentCode = departmentCode; return this; }

        public AuthResponseDTO build() {
            return new AuthResponseDTO(token, tokenType, userId, email, fullName, role, departmentCode);
        }
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getTokenType() { return tokenType; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getDepartmentCode() { return departmentCode; }
    public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }
}
