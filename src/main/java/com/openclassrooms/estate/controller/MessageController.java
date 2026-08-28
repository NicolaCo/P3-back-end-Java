package com.openclassrooms.estate.controller;

import com.openclassrooms.estate.dto.MessageRequest;
import com.openclassrooms.estate.model.User;
import com.openclassrooms.estate.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody MessageRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        messageService.create(request, user);
        return ResponseEntity.ok(Map.of("message", "Message send with success"));
    }
}