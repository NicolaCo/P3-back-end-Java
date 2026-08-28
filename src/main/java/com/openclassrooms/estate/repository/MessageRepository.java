package com.openclassrooms.estate.repository;

import com.openclassrooms.estate.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}