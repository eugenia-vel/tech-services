package com.cdpo.techservices.services;

import com.cdpo.techservices.dto.NotificationDTO;
import com.cdpo.techservices.entity.TechWork;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotifierClient {
    private final RestClient notifierClient;

    public void sendNotification(TechWork booking) {
        notifierClient.post()
                .uri("/notify")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new NotificationDTO(createMessage(booking)))
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(), (request, response) -> {
                    log.error(response.getStatusText());
                });
    }

    public String createMessage(TechWork booking) {
        // TODO::прописать логику создания сообщения
        return "";
    }
}
