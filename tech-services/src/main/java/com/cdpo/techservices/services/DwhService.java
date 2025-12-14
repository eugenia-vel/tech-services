package com.cdpo.techservices.services;

import com.cdpo.techservices.dto.BookingDwhDTO;

import java.util.List;

public interface DwhService{
    List<Long> sendStatistics(List<BookingDwhDTO> bookingsList);
}