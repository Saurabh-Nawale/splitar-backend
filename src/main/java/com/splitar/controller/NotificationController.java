package com.splitar.controller;

import com.splitar.model.Notification;
import com.splitar.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{username}")
    public List<Notification> getNotifications(@PathVariable String username) {
        return notificationService.getNotificationsForUser(username);
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.saveNotification(notification);
    }

    @PutMapping("/{id}")
    public Optional<Notification> updateStatus(@PathVariable String id, @RequestBody String status) {
        return notificationService.updateStatus(id, status);
    }
}
