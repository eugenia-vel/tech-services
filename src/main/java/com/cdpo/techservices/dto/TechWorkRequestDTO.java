package com.cdpo.techservices.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record TechWorkRequestDTO(
        @NotEmpty
        @NotNull
        @NotBlank
        String serviceName,

        @NotEmpty
        @NotNull
        @NotBlank
        String workerName,

        @Future
        LocalDateTime dateTime,

        @NotEmpty
        @NotNull
        @NotBlank
        String address
) {
}
