package com.openclassrooms.estate.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWT returned after a successful registration or login")
public class TokenResponse {

    @Schema(description = "Bearer JWT to use in the Authorization header", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIn0.abc123")
    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
