package com.railway.blockoptimizer.service;

import com.railway.blockoptimizer.domain.entity.User;
import com.railway.blockoptimizer.dto.AuthResponseDTO;
import com.railway.blockoptimizer.dto.LoginRequestDTO;
import com.railway.blockoptimizer.dto.UserDTO;
import com.railway.blockoptimizer.exception.ResourceNotFoundException;
import com.railway.blockoptimizer.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + loginRequest.getEmail()));

        String mockJwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.supabase_user_" + user.getId();

        return AuthResponseDTO.builder()
                .token(mockJwt)
                .tokenType("Bearer")
                .userId(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole().name())
                .departmentCode(user.getDepartment() != null ? user.getDepartment().getCode() : null)
                .build();
    }

    public UserDTO getUserProfile(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole().name())
                .departmentId(user.getDepartment() != null ? user.getDepartment().getId() : null)
                .departmentName(user.getDepartment() != null ? user.getDepartment().getName() : null)
                .build();
    }
}
