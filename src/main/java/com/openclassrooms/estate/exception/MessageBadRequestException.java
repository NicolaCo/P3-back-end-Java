package com.openclassrooms.estate.exception;

public class MessageBadRequestException extends RuntimeException {

    public MessageBadRequestException(String message) {
        super(message);
    }
}