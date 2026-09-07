package com.openclassrooms.estate.controller;

import com.openclassrooms.estate.dto.LoginRequest;
import com.openclassrooms.estate.dto.RegisterRequest;
import com.openclassrooms.estate.dto.TokenResponse;
import com.openclassrooms.estate.dto.UserResponse;
import com.openclassrooms.estate.model.User;
import com.openclassrooms.estate.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "Registration, login and authenticated profile")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user and return a JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registration successful, returns a JWT"),
            @ApiResponse(responseCode = "400", description = "Invalid data or email already in use", content = @Content)
    })
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate and return a JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful, returns a JWT"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content(schema = @Schema(example = "{\"message\": \"error\"}")))
    })
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    @Operation(summary = "Get the profile of the authenticated user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returns the profile of the authenticated user"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<UserResponse> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(UserResponse.from(user));
    }
}
