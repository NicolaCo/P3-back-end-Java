package com.openclassrooms.estate.service;

import com.openclassrooms.estate.exception.ResourceNotFoundException;
import com.openclassrooms.estate.model.User;
import com.openclassrooms.estate.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}