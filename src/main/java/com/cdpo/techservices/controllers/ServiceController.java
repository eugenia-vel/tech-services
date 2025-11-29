package com.cdpo.techservices.controllers;

import com.cdpo.techservices.dto.ServiceDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v1/service")
@RestController
public class ServiceController {
    private List<ServiceDTO> serviceList = new ArrayList<>();

    @PostMapping
    public int createService (@RequestBody() ServiceDTO serviceDTO) {
        List<ServiceDTO> serviceTemp = new ArrayList<>(serviceList);
        serviceTemp.add(serviceDTO);
        serviceList = serviceTemp;
        System.out.println(serviceList);
        return 0;
    }

    @GetMapping
    public ServiceDTO getServiceById (@RequestParam int id) {
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
    public ServiceDTO editService (@PathVariable int id,
                                   @RequestParam String serviceName,
                                   @RequestParam String workerName){
        for (int i = 0; i < serviceList.size(); i++) {
            ServiceDTO tempService = new ServiceDTO(id, serviceName, workerName);
            if (serviceList.get(i).getId() == id) {
                serviceList.set(i, tempService);
                return serviceList.get(i);
            }
        }
        return null;
    }
}
