package com.cdpo.dwh.controllers;

import com.cdpo.dwh.dto.BookingDTO;
import com.cdpo.dwh.exceptions.BookingException;
import com.cdpo.dwh.services.DwhService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
public class DwhController {

    public DwhService dwhService;
    // Метод получает от Клиента данные о всех услугах, предоставленных за день.
    // Обрабатывает их в сервисе, возвращает id услуг, которые учтены в статистике.
    // В случае, если возникает ошибка, отправить Клиенту информацию об ошибке
    @PostMapping
    public ResponseEntity<?> sendStatistics(@RequestBody List<BookingDTO> allBookings) {
        try{
            return new ResponseEntity<>(dwhService.processData(allBookings), HttpStatus.OK);
        } catch (BookingException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }
}
