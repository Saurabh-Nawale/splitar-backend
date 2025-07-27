package com.splitar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;
    private String senderUsername;
    private String receiverUsername;
    private String ridePostId;
    private String message;
    private String status; // e.g., "pending", "accepted", "declined"
    private String type; // e.g., "request", "accepted", "declined"
    private String timestamp;

    public void setStatus(String newStatus) {
    }

    // Constructors, Getters, Setters
}
