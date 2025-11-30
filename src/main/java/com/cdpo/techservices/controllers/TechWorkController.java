package com.cdpo.techservices.controllers;

import com.cdpo.techservices.dto.ServiceDTO;
import com.cdpo.techservices.dto.TechWorkRequestDTO;
import com.cdpo.techservices.dto.TechWorkResponseDTO;
import com.cdpo.techservices.services.TechWorkService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        return new ResponseEntity<>(techWorkService.bookService(techWorkDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable("id") @Positive int id) {
        return new ResponseEntity<>(techWorkService.cancelBooking(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editBooking(@PathVariable("id") @Positive int id,
                                         @RequestParam LocalDateTime dateTime){
        return new ResponseEntity<>(techWorkService.editBooking(id, dateTime), HttpStatus.OK);
    }

    @GetMapping("/my-bookings")
    public List<TechWorkResponseDTO> getAllBookings() {
        return techWorkService.getAllBookings();
    }

    @GetMapping("/past-services")
    public List<TechWorkResponseDTO> getPastBookings() {
        return techWorkService.getPastServices();
    }
}
