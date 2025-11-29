package com.cdpo.techservices.controllers;

import com.cdpo.techservices.dto.ServiceDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Validated
@RequestMapping("/api/v1/service")
@RestController
public class ServiceController {
    private List<ServiceDTO> serviceList = new ArrayList<>();

    @PostMapping
    public int createService (@RequestBody() @Valid ServiceDTO serviceDTO) {
        List<ServiceDTO> serviceTemp = new ArrayList<>(serviceList);
        serviceTemp.add(serviceDTO);
        serviceList = serviceTemp;
        System.out.println(serviceList);
        return 0;
    }

    @GetMapping
    public ServiceDTO getServiceById (@RequestParam @Positive int id) {
        for (ServiceDTO service : serviceList) {
            if (service.getId() == id) {
                return service;
            }
        }
        return null;
    }

    @GetMapping("/services")
    public List<ServiceDTO> getListOfServices () {
        return serviceList;
    }

    @PutMapping("/{id}")
    public ServiceDTO editService (@PathVariable @Positive int id,
                                   @RequestParam(required = false) @NotBlank @NotEmpty String serviceName,
                                   @RequestParam(required = false) @NotBlank @NotEmpty String workerName){
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).getId() == id) {
                if (serviceName == null){
                    serviceName = serviceList.get(i).getServiceName();
                }
                if (workerName == null){
                    workerName = serviceList.get(i).getWorkerName();
                }
                ServiceDTO tempService = new ServiceDTO(id, serviceName, workerName);
                serviceList.set(i, tempService);
                return serviceList.get(i);
            }
        }
        return null;
    }
}
