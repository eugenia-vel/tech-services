package com.cdpo.techservices.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record TechWorkResponseDTO(
        String serviceName,
        String workerName,
        LocalDateTime appointmentTime,
        Float serviceTime,
        String address,
        Float cost
) {
}
