package com.cdpo.techservices.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record TechWorkRequestDTO(

        @NotBlank
        String serviceName,

        @NotBlank
        String workerName,

        @NotNull
        @Future
        LocalDateTime appointmentTime,

        @NotEmpty
        @Positive
        Float serviceTime,

        @NotEmpty
        @NotNull
        @NotBlank
        String address
) {
}
