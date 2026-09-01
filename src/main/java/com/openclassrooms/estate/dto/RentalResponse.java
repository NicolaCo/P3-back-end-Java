package com.openclassrooms.estate.dto;

import com.openclassrooms.estate.model.Rental;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.format.DateTimeFormatter;

@Schema(description = "Rental details")
public class RentalResponse {

    @Schema(description = "Rental id", example = "1")
    private Integer id;
    @Schema(description = "Rental name", example = "T3 House")
    private String name;
    @Schema(description = "Surface in square meters", example = "75.0")
    private Double surface;
    @Schema(description = "Monthly price in euros", example = "850.0")
    private Double price;
    @Schema(description = "Picture URL", example = "/images/3f2b3c1e-ec61-42a7-9d9a-c3ee4d2b1f0a.png")
    private String picture;
    @Schema(description = "Rental description", example = "Bright apartment near shops")
    private String description;
    @Schema(description = "Id of the owner user", example = "1")
    private Integer owner_id;
    @Schema(description = "Creation date, format yyyy/MM/dd", example = "2026/09/01")
    private String created_at;
    @Schema(description = "Last update date, format yyyy/MM/dd", example = "2026/09/01")
    private String updated_at;

    public RentalResponse() {
    }

    public RentalResponse(Integer id, String name, Double surface, Double price, String picture,
                          String description, Integer ownerId, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.owner_id = ownerId;
        this.created_at = createdAt;
        this.updated_at = updatedAt;
    }

    public static RentalResponse from(Rental rental) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return new RentalResponse(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                rental.getPicture(),
                rental.getDescription(),
                rental.getOwner().getId(),
                rental.getCreatedAt().format(formatter),
                rental.getUpdatedAt().format(formatter)
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
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