package com.cdpo.techservices.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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
    public ServiceDTO(int id, String serviceName, String workerName) {
        this.id = id;
        this.serviceName = serviceName;
        this.workerName = workerName;
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
}