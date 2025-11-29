package com.cdpo.techservices.dto;

public class ServiceDTO {
    private int id;
    private String serviceName;
    private String workerName;
    public ServiceDTO(int id, String serviceName, String workerName) {
        this.id = id;
        this.serviceName = serviceName;
        this.workerName = workerName;
    }

    public int getId() {
        return id;
    }
}