package com.cdpo.techservices.controllers;

import com.cdpo.techservices.dto.TechWorkRequestDTO;
import com.cdpo.techservices.dto.TechWorkResponseDTO;
import com.cdpo.techservices.services.TechWorkService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Validated
@RequestMapping("/api/v1/techwork")
@RestController
public class TechWorkController {

    private final TechWorkService techWorkService;

    public TechWorkController(TechWorkService techWorkService) {
        this.techWorkService = techWorkService;
    }

    @PostMapping
    public ResponseEntity<?> bookService(@RequestBody @Valid TechWorkRequestDTO techWorkDTO){
        log.debug("POST request. Booking a service: {}", techWorkDTO);
        URI uri = URI.create("/api/v1/techwork/" +
                techWorkService.bookService(techWorkDTO));
        return ResponseEntity.created(uri)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable("id") @NotNull @Positive Long id) {
        log.debug("DELETE request");
        return new ResponseEntity<>(techWorkService.cancelBooking(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editBooking(@PathVariable("id") @Positive int id,
                                         @RequestParam LocalDateTime dateTime) {
        return new ResponseEntity<>(techWorkService.editBooking(id, dateTime), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable("id") @Positive Long id) {
        return new ResponseEntity<>(techWorkService.getBookingById(id), HttpStatus.OK);
    }

    @GetMapping("/my-bookings")
    public List<TechWorkResponseDTO> getAllBookings() {
        return techWorkService.getAllBookings();
    }

    @GetMapping("/past-services")
    public List<TechWorkResponseDTO> getPastBookings() {
        return techWorkService.getPastServices();
    }

    @GetMapping("/{appointment-time}")
    public List<TechWorkResponseDTO> getBookingsByTime(@PathVariable("appointment-time") LocalDateTime appointmentTime) {
        return techWorkService.getBookingsByTime(appointmentTime);
    }

    @GetMapping("/date")
    public int getRevenueByDate(@PathVariable("date") LocalDate date) {
        return techWorkService.getRevenueByDate(date);
    }
}
