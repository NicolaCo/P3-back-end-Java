package com.openclassrooms.estate.dto;

import com.openclassrooms.estate.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.format.DateTimeFormatter;

@Schema(description = "Public user details")
public class UserResponse {

    @Schema(description = "User id", example = "1")
    private Integer id;
    @Schema(description = "User email", example = "john.doe@example.com")
    private String email;
    @Schema(description = "User name", example = "John Doe")
    private String name;
    @Schema(description = "Creation date, format yyyy/MM/dd", example = "2026/09/01")
    private String created_at;
    @Schema(description = "Last update date, format yyyy/MM/dd", example = "2026/09/01")
    private String updated_at;

    public UserResponse(Integer id, String email, String name, String createdAt, String updatedAt ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.created_at = createdAt;
        this.updated_at = updatedAt;
    }

    public static UserResponse from(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getCreatedAt().format(formatter),
                user.getUpdatedAt().format(formatter)
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}