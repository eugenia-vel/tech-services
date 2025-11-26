package com.cdpo.techservices.controllers;

import com.cdpo.techservices.dto.ServiceResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class ServiceController {
    @PostMapping
    public int createService () {
        return 0;
    }

    @GetMapping
    public ServiceResponseDTO getServiceById () {
        return null;
    }
}
