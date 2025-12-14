package com.cdpo.dwh.services;

import com.cdpo.dwh.dto.BookingDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DwhService {

    public Long[] processData(List<BookingDTO> allBookings) {
        // TODO: прописать логику обработки поступившей информации
        Long[] bookingIds = new Long[allBookings.size()];
        for (int i = 0; i < allBookings.size(); i++) {
            bookingIds[i] = allBookings.get(i).id();
        }
        return bookingIds;
    }
}
