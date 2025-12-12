package com.cdpo.notifier.controller;

import com.cdpo.notifier.dto.UserNotification;
import com.cdpo.notifier.exception.NotificationException;
import com.cdpo.notifier.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifier")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public ResponseEntity<Void> sendNotification(@RequestBody UserNotification userNotification) {
        if (userNotification.userId() == null || userNotification.message() == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            notificationService.sendNotification(userNotification);
            return ResponseEntity.ok().build();
        } catch (NotificationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
