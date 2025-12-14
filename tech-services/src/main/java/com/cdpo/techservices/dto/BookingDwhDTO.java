package com.cdpo.techservices.dto;

import com.cdpo.techservices.constants.BookingStatus;

import java.time.LocalDateTime;

public record BookingDwhDTO(
        Long id,
        String serviceName,
        String workerName,
        BookingStatus status,
        float priceWithDiscount,
        LocalDateTime appointmentTime,
        String address,
        Float serviceTime,
        Long userId
) {
}
