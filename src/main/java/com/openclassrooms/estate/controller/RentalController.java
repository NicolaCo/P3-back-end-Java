package com.openclassrooms.estate.controller;

import com.openclassrooms.estate.dto.RentalResponse;
import com.openclassrooms.estate.model.User;
import com.openclassrooms.estate.service.RentalService;
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

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<Map<String, List<RentalResponse>>> getRentals() {
        return ResponseEntity.ok(Map.of("rentals", rentalService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponse> getRental(@PathVariable Integer id) {
        return ResponseEntity.ok(rentalService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createRental(@RequestParam String name,
                                                            @RequestParam Double surface,
                                                            @RequestParam Double price,
                                                            @RequestPart("picture") MultipartFile picture,
                                                            @RequestParam String description) {
        rentalService.create(name, surface, price, picture, description, currentUser());
        return ResponseEntity.ok(Map.of("message", "Rental created !"));
    }

    @PutMapping("/{id}")
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