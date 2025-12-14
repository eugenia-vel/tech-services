package com.cdpo.techservices.services;

import com.cdpo.techservices.dto.BookingDwhDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Service
@Profile("rest")
@AllArgsConstructor
public class DwhRestService implements DwhService{
    private final RestClient notifierClient;
    public List<Long> sendStatistics(List<BookingDwhDTO> bookingsList){
        notifierClient.post()
                .uri("/statistics")
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookingsList)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(), (request, response) -> {
                    log.error(response.getStatusText());
                });
        //TODO:: Добавить реализацию ответа
        return null;
    }
}
