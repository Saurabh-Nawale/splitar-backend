package com.splitar.service;

import com.splitar.model.Message;
import com.splitar.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByRidePostId(String ridePostId) {
        return messageRepository.findByRidePostId(ridePostId);
    }
}
