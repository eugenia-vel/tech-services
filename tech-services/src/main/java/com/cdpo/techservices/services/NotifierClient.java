package com.cdpo.techservices.services;

import com.cdpo.techservices.entity.TechWork;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@AllArgsConstructor
@Service
public class NotifierClient {
    private final RestClient notifierClient;

    public void sendNotification(TechWork booking) {
        notifierClient.get()
    }
}
