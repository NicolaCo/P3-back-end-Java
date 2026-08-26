package com.openclassrooms.estate.dto;

public class UserResponse {

    private Integer id;
    private String email;
    private String name;
    private String created_at;
    private String updated_at;

    public UserResponse(Integer id, String email, String name, String createdAt, String updatedAt ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.created_at = createdAt;
        this.updated_at = updatedAt;
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
