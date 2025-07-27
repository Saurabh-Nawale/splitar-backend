package com.splitar.service;

import com.splitar.model.Notification;
import com.splitar.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotificationsForUser(String username) {
        return notificationRepository.findByReceiverUsername(username);
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Optional<Notification> updateStatus(String id, String newStatus) {
        Optional<Notification> optional = notificationRepository.findById(id);
        if (optional.isPresent()) {
            Notification notif = optional.get();
            notif.setStatus(newStatus);
            return Optional.of(notificationRepository.save(notif));
        }
        return Optional.empty();
    }
}
