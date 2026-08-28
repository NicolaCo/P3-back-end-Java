package com.openclassrooms.estate.repository;

import com.openclassrooms.estate.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}