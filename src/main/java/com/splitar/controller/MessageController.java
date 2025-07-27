package com.splitar.controller;

import com.splitar.model.Message;
import com.splitar.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @GetMapping("/{ridePostId}")
    public List<Message> getMessages(@PathVariable String ridePostId) {
        return messageService.getMessagesByRidePostId(ridePostId);
    }
}
