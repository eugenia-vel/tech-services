package com.cdpo.techservices.dto;

public class ServiceDTO {
    private final int id;
    private String serviceName;
    private String workerName;
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