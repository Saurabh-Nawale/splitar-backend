package com.splitar.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;



@Document(collection = "notifications")
@Getter
@Setter
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
    private boolean read ;
    private Instant createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getRidePostId() {
        return ridePostId;
    }

    public void setRidePostId(String ridePostId) {
        this.ridePostId = ridePostId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // ✅ FIXED: Added the missing getter and setter for the 'read' field
    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    // Constructors, Getters, Setters
}
