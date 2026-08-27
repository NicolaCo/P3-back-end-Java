package com.openclassrooms.estate.controller;

import com.openclassrooms.estate.dto.LoginRequest;
import com.openclassrooms.estate.dto.RegisterRequest;
import com.openclassrooms.estate.dto.TokenResponse;
import com.openclassrooms.estate.dto.UserResponse;
import com.openclassrooms.estate.model.User;
import com.openclassrooms.estate.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest request) {
        String token = authService.register(request);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getEmail(), user.getName(), user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), user.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))));
    }
}
