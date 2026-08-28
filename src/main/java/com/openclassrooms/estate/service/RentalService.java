package com.openclassrooms.estate.service;

import com.openclassrooms.estate.dto.RentalResponse;
import com.openclassrooms.estate.exception.ResourceNotFoundException;
import com.openclassrooms.estate.model.Rental;
import com.openclassrooms.estate.model.User;
import com.openclassrooms.estate.repository.RentalRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final PictureStorageService pictureStorage;

    public RentalService(RentalRepository rentalRepository, PictureStorageService pictureStorage) {
        this.rentalRepository = rentalRepository;
        this.pictureStorage = pictureStorage;
    }

    public List<RentalResponse> getAll() {
        return rentalRepository.findAll().stream()
                .map(RentalResponse::from)
                .toList();
    }

    public RentalResponse getById(Integer id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", id));
        return RentalResponse.from(rental);
    }

    public void create(String name, Double surface, Double price, MultipartFile picture,
                       String description, User owner) {
        Rental rental = new Rental();
        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);
        rental.setPicture(pictureStorage.store(picture));
        rental.setDescription(description);
        rental.setOwner(owner);
        rentalRepository.save(rental);
    }

    public void update(Integer id, String name, Double surface, Double price,
                       String description, User currentUser) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", id));
        if (!rental.getOwner().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You do not own this rental");
        }
        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);
        rental.setDescription(description);
        rentalRepository.save(rental);
    }
}