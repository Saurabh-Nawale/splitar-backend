package com.splitar.service;

import com.splitar.model.Notification;
import com.splitar.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(Notification notification) {
        notification.setRead(false); // New notifications are always unread
        notification.setCreatedAt(Instant.now()); // Set the current timestamp
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsForUser(String username) {
        return notificationRepository.findByReceiverUsernameOrderByCreatedAtDesc(username);
    }
}
