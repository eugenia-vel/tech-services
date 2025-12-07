package com.cdpo.techservices.repository;

import com.cdpo.techservices.entity.TechWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TechWorkRepository extends JpaRepository<TechWork, Long> {
//
//    @Query(nativeQuery = true,
//    value = "SELECT * FROM tech_work" +
//            "WHERE id = :id")
//    TechWork getBookingById(Long id);

//    @Query(nativeQuery = true,
//    value = "")

    @Query(nativeQuery = true,
    value = "SELECT * FROM tech_work" +
    "WHERE appointment_time < :currentDate")
    List<TechWork> findPastBookings(LocalDateTime currentDate);

    @Query(nativeQuery = true,
    value = "SELECT * FROM tech_work" +
            "WHERE appointment_time > :currentDate")
    List<TechWork> findFutureBookings(LocalDateTime currentDate);

    @Query(nativeQuery = true,
    value = "SELECT * FROM tech_work" +
            "WHERE appointment_time = :appointmentTime")
    List<TechWork> findBookingsByTime(LocalDateTime appointmentTime);

    @Query(nativeQuery = true,
    value = "SELECT * FROM tech_work" +
            "WHERE appointment_time >= :dayStart" +
            "AND appointment_time <= :dayEnd")
    List<TechWork> findAllBookingsInADay(LocalDateTime dayStart, LocalDateTime dayEnd);
}
