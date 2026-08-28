package com.openclassrooms.estate.service;

import com.openclassrooms.estate.dto.MessageRequest;
import com.openclassrooms.estate.exception.MessageBadRequestException;
import com.openclassrooms.estate.model.Message;
import com.openclassrooms.estate.model.User;
import com.openclassrooms.estate.repository.MessageRepository;
import com.openclassrooms.estate.repository.RentalRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final RentalRepository rentalRepository;

    public MessageService(MessageRepository messageRepository, RentalRepository rentalRepository) {
        this.messageRepository = messageRepository;
        this.rentalRepository = rentalRepository;
    }

    public void create(MessageRequest request, User user) {
        if (request.getRental_id() == null || request.getMessage() == null || request.getMessage().isBlank()) {
            throw new MessageBadRequestException("Rental id and message are required");
        }
        if (!rentalRepository.existsById(request.getRental_id())) {
            throw new MessageBadRequestException("Rental does not exist");
        }
        Message message = new Message();
        message.setRentalId(request.getRental_id());
        message.setUserId(user.getId());
        message.setMessage(request.getMessage());
        messageRepository.save(message);
    }
}