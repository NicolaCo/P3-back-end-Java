package com.openclassrooms.estate.controller;

import com.openclassrooms.estate.dto.MessageRequest;
import com.openclassrooms.estate.model.User;
import com.openclassrooms.estate.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "Messages", description = "Send a message to a rental owner")
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    @Operation(summary = "Send a message to a rental owner")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Message sent successfully",
                    content = @Content(schema = @Schema(example = "{\"message\": \"Message send with success\"}"))),
            @ApiResponse(responseCode = "400", description = "Invalid message or rental does not exist",
                    content = @Content(schema = @Schema(example = "{}"))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody MessageRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        messageService.create(request, user);
        return ResponseEntity.ok(Map.of("message", "Message send with success"));
    }
}