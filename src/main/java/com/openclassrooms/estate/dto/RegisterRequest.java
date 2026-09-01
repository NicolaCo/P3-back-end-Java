package com.openclassrooms.estate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Payload to create a new account")
public class RegisterRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Schema(description = "User email", example = "john.doe@example.com")
    private String email;

    @NotBlank(message = "Name is required")
    @Schema(description = "User name", example = "John Doe")
    private String name;

    @NotBlank(message = "Password is required")
    @Size(min = 1, message = "Password must be at least 1 character")
    @Schema(description = "User password", example = "password123")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
