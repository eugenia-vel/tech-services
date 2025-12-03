package com.cdpo.techservices.repository;

import com.cdpo.techservices.entity.TechWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TechWorkRepository extends JpaRepository<TechWork, Long> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM TechWork" +
    "WHERE AppointmentTime < :currentDate")
    List<TechWork> findPastBookings(LocalDateTime currentDate);
}
