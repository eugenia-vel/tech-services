package com.cdpo.techservices.controllers;

import com.cdpo.techservices.dto.TechWorkRequestDTO;
import com.cdpo.techservices.dto.TechWorkResponseDTO;
import com.cdpo.techservices.exceptions.TechWorkException;
import com.cdpo.techservices.services.TechWorkService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            return new ResponseEntity<>(techWorkService.cancelBooking(id), HttpStatus.OK);
        } catch (TechWorkException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editBooking(@PathVariable("id") @Positive Long id,
                                         @RequestParam LocalDateTime dateTime) {
        try {
            return new ResponseEntity<>(techWorkService.editBooking(id, dateTime), HttpStatus.OK);
        }  catch (TechWorkException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeBookingInfo(@PathVariable("id") @Positive Long id,
                                               @RequestParam TechWorkRequestDTO requestDTO) {
        try {
            return new ResponseEntity<>(techWorkService.changeBookingInfo(id, requestDTO), HttpStatus.OK);
        } catch (TechWorkException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/{id}/discount")
    public ResponseEntity<?> setDiscount(@PathVariable("id") @Positive Long id, int discount) {
        try {
            return new ResponseEntity<>(techWorkService.setDiscount(id, discount), HttpStatus.OK);
        } catch (TechWorkException e){
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable("id") @Positive Long id) {
        try {
            return new ResponseEntity<>(techWorkService.getBookingById(id), HttpStatus.OK);
        } catch (TechWorkException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/my-bookings")
    public List<TechWorkResponseDTO> getAllBookings() {
        try {
            return techWorkService.getAllBookings();
        } catch (TechWorkException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/past-services")
    public List<TechWorkResponseDTO> getPastBookings() {
        try {
            return techWorkService.getPastServices();
        } catch (TechWorkException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/{appointment-time}")
    public List<TechWorkResponseDTO> getBookingsByTime(@PathVariable("appointment-time") LocalDateTime appointmentTime) {
        try {
            return techWorkService.getBookingsByTime(appointmentTime);
        } catch (TechWorkException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/date")
    public int getRevenueByDate(@PathVariable("date") LocalDate date) {
        try {
            return techWorkService.getRevenueByDate(date);
        } catch (TechWorkException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }
}
