package com.cdpo.techservices.services;

import com.cdpo.techservices.clients.DwhFeignClient;
import com.cdpo.techservices.dto.BookingDwhDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("feign")
@RequiredArgsConstructor
public class DwhFeignService implements DwhService{

    private final DwhFeignClient dwhFeignClient;

    @Override
    public List<Long> sendStatistics(List<BookingDwhDTO> bookingsList) {
        return dwhFeignClient.sendStatistics(bookingsList);
    }

}
