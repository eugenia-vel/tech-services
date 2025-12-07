package com.cdpo.techservices.services;

import com.cdpo.techservices.dto.TechWorkRequestDTO;
import com.cdpo.techservices.dto.TechWorkResponseDTO;
import com.cdpo.techservices.mapper.TechWorkMapper;
import com.cdpo.techservices.repository.ServiceCategoryRepository;
import com.cdpo.techservices.repository.TechWorkRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TechWorkService {

    private final TechWorkRepository techWorkRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;
    private final TechWorkMapper techWorkMapper;

    public int bookService(TechWorkRequestDTO techWork) {
        // TODO:: add to database
        return 0;
    }
    public int cancelBooking() {
        // TODO:: remove from a database
        return 0;
    }
    public TechWorkResponseDTO editBooking(int id, LocalDateTime newDateTime) {
        //TODO:: change booking info in a db
        return null;
    }
    public TechWorkResponseDTO getBookingById(int id) {
        //TODO: find in a db by id
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
    public List<TechWorkResponseDTO> getBookingsByTime() {
        //TODO:: list all bookings by chosen time
        return null;
    }
    public int getRevenueByDate(){
        return 0;
    }
}
