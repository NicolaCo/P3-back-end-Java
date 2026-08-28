package com.openclassrooms.estate.dto;

public class MessageRequest {

    private Integer rental_id;
    private String message;

    public MessageRequest() {
    }

    public MessageRequest(Integer rental_id, String message) {
        this.rental_id = rental_id;
        this.message = message;
    }

    public Integer getRental_id() {
        return rental_id;
    }

    public void setRental_id(Integer rental_id) {
        this.rental_id = rental_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}