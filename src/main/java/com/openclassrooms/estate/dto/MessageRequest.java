package com.openclassrooms.estate.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Message sent to the owner of a rental. " +
        "The sender is the authenticated user, so user_id is not part of the request body.")
public class MessageRequest {

    @Schema(description = "Id of the rental the message concerns", example = "1")
    private Integer rental_id;

    @Schema(description = "Message content", example = "Hello, is the rental still available?")
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