package com.cdpo.techservices.dto;

public class ServiceResponseDTO {
    private int idCounter = 1;
    private int id;
    private String name;
    ServiceResponseDTO(String serviceName) {
        name = serviceName;
        id = idCounter;
        idCounter += 1;
    }
}
