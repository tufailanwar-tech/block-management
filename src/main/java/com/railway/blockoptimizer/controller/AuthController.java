package com.railway.blockoptimizer.controller;

import com.railway.blockoptimizer.dto.AuthResponseDTO;
import com.railway.blockoptimizer.dto.LoginRequestDTO;
import com.railway.blockoptimizer.dto.UserDTO;
import com.railway.blockoptimizer.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(@RequestParam UUID userId) {
        return ResponseEntity.ok(authService.getUserProfile(userId));
    }
}
