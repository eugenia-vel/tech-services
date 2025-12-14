package com.cdpo.techservices.services;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Service
@EnableScheduling
public class DwhSchedule {
    private final DwhService dwhService;
    private final TechWorkService techWorkService;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    public List<Long> getStatisticsBySchedule() {
        return dwhService.sendStatistics(techWorkService.getYesterdayBookings());
    }
}
