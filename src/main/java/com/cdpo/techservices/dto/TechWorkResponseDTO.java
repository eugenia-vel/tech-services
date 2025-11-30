package com.cdpo.techservices.dto;

import java.time.LocalDateTime;

public record TechWorkResponseDTO(
        String serviceName,
        String workerName,
        LocalDateTime dateTime,
        String address
) {
}
