package com.cdpo.techservices.repository;

import com.cdpo.techservices.entity.ServiceCategory;

import java.util.Optional;

public interface ServiceCategoryRepository {
    ServiceCategory findByServiceName(String serviceName);
}
