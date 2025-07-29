package com.splitar.repository;

import com.splitar.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    // Finds notifications for a user, sorted with the newest first
    List<Notification> findByReceiverUsernameOrderByCreatedAtDesc(String receiverUsername);
}

