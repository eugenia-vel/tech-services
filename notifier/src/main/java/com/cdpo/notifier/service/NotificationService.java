package com.cdpo.notifier.service;

import com.cdpo.notifier.client.UserClient;
import com.cdpo.notifier.dto.UserNotification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Value("${send.from}")
    private final String sendFrom;
    private final JavaMailSender javaMailSender;
    private final UserClient userClient;

    public NotificationService(String sendFrom, JavaMailSender javaMailSender, UserClient userClient) {
        this.sendFrom = sendFrom;
        this.javaMailSender = javaMailSender;
        this.userClient = userClient;
    }

    public void sendNotification(UserNotification userNotification) {
        String userEmail = userClient.getUserDataById(userNotification.userId())
                .email();
        javaMailSender.send(createMailMessage(userNotification.message(),
                new String[]{userEmail}));
    }
    private SimpleMailMessage createMailMessage(String message, String[] receiver){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sendFrom);
        mailMessage.setTo(receiver);
        mailMessage.setSubject("Notification");
        mailMessage.setText(message);
        return mailMessage;
    }
}
