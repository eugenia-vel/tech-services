package com.cdpo.dwh.dto;

import com.cdpo.dwh.constants.BookingStatus;

import java.time.LocalDateTime;

public record BookingDTO (Long id,
                          String serviceName,
                          String workerName,
                          BookingStatus status,
                          float priceWithDiscount,
                          LocalDateTime appointmentTime,
                          String address,
                          Float serviceTime,
                          Long userId) {}
