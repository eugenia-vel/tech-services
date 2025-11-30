package com.cdpo.techservices.services;

import com.cdpo.techservices.dto.TechWorkRequestDTO;
import com.cdpo.techservices.dto.TechWorkResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechWorkService {
    public int bookService(TechWorkRequestDTO techWork) {
        // TODO:: add to database
        return 0;
    }
    public int cancelBooking() {
        // TODO:: remove from a database
        return 0;
    }
    public TechWorkResponseDTO editBooking(int id) {
        //TODO:: change booking info in a db
        return null;
    }
    public List<TechWorkResponseDTO> getAllBookings() {
        //TODO:: list all future bookings
        return null;
    }
    public List<TechWorkResponseDTO> getPastServices() {
        //TODO:: list all past bookings
        return null;
    }
}
