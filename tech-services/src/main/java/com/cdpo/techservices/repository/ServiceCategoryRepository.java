package com.cdpo.techservices.repository;

import com.cdpo.techservices.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
    Optional<ServiceCategory> findByServiceName(String serviceName);
}
