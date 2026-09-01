package com.openclassrooms.estate.controller;

import com.openclassrooms.estate.dto.RentalResponse;
import com.openclassrooms.estate.model.User;
import com.openclassrooms.estate.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Tag(name = "Rentals", description = "List, create, retrieve and update rentals")
@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    @Operation(summary = "List all rentals")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returns the list of all rentals"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<Map<String, List<RentalResponse>>> getRentals() {
        return ResponseEntity.ok(Map.of("rentals", rentalService.getAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a rental by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Returns the requested rental"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Rental not found",
                    content = @Content(schema = @Schema(example = "{}")))
    })
    public ResponseEntity<RentalResponse> getRental(@PathVariable Integer id) {
        return ResponseEntity.ok(rentalService.getById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create a rental (multipart)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rental created successfully",
                    content = @Content(schema = @Schema(example = "{\"message\": \"Rental created !\"}"))),
            @ApiResponse(responseCode = "400", description = "Invalid form data or missing picture", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<Map<String, String>> createRental(@Parameter @RequestParam String name,
                                                            @Parameter @RequestParam Double surface,
                                                            @Parameter @RequestParam Double price,
                                                            @Parameter(schema = @Schema(type = "string", format = "binary")) @RequestPart("picture") MultipartFile picture,
                                                            @Parameter @RequestParam String description) {
        rentalService.create(name, surface, price, picture, description, currentUser());
        return ResponseEntity.ok(Map.of("message", "Rental created !"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a rental")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rental updated successfully",
                    content = @Content(schema = @Schema(example = "{\"message\": \"Rental updated !\"}"))),
            @ApiResponse(responseCode = "400", description = "Invalid form data", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Only the owner can update the rental",
                    content = @Content(schema = @Schema(example = "{}"))),
            @ApiResponse(responseCode = "404", description = "Rental not found",
                    content = @Content(schema = @Schema(example = "{}")))
    })
    public ResponseEntity<Map<String, String>> updateRental(@PathVariable Integer id,
                                                            @RequestParam String name,
                                                            @RequestParam Double surface,
                                                            @RequestParam Double price,
                                                            @RequestParam String description) {
        rentalService.update(id, name, surface, price, description, currentUser());
        return ResponseEntity.ok(Map.of("message", "Rental updated !"));
    }

    private User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}