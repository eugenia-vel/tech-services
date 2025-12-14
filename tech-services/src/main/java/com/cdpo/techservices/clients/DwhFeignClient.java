package com.cdpo.techservices.clients;

import com.cdpo.techservices.dto.BookingDwhDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Profile("feign")
@FeignClient(value = "dwh", url = "${dwh.url}")
public interface DwhFeignClient {
    @PostMapping("/statistics")
    List<Long> sendStatistics(@RequestBody List<BookingDwhDTO> bookingsList);
}