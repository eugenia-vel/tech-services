package com.cdpo.techservices.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServiceDTO {
    @Positive
    private final int id;
    @NotBlank
    @NotEmpty
    @NotNull
    private final String serviceName;
    @NotBlank
    @NotEmpty
    @NotNull
    private final String workerName;
    private final LocalDateTime dateTime;
    public ServiceDTO(int id, String serviceName, String workerName, LocalDateTime dateTime) {
        this.id = id;
        this.serviceName = serviceName;
        this.workerName = workerName;
        this.dateTime = dateTime;
    }

    public int getId() {
        return this.id;
    }
    public String getServiceName() {
        return this.serviceName;
    }
    public String getWorkerName() {
        return this.workerName;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}