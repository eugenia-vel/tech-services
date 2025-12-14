package com.cdpo.techservices.services;

import com.cdpo.techservices.dto.BookingDwhDTO;
import com.cdpo.techservices.dto.TechWorkRequestDTO;
import com.cdpo.techservices.dto.TechWorkResponseDTO;
import com.cdpo.techservices.entity.ServiceCategory;
import com.cdpo.techservices.entity.TechWork;
import com.cdpo.techservices.exceptions.TechWorkException;
import com.cdpo.techservices.mapper.TechWorkMapper;
import com.cdpo.techservices.repository.ServiceCategoryRepository;
import com.cdpo.techservices.repository.TechWorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.cdpo.techservices.constants.BookingStatus.COMPLETED;

@RequiredArgsConstructor
@Service
public class TechWorkService {

    private final TechWorkRepository techWorkRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;
    private final TechWorkMapper techWorkMapper;

    public Long bookService(TechWorkRequestDTO techWorkRequest) {
        ServiceCategory serviceCategory = serviceCategoryRepository
                .findByServiceName(techWorkRequest.serviceName())
                .get();
        TechWork techWork = techWorkMapper.mapToEntity(techWorkRequest);
        techWork.setServiceCategory(serviceCategory);
        techWorkRepository.save(techWork);
        return techWork.getId();
    }

    public int cancelBooking(Long id) {
        techWorkRepository.deleteById(id);
        return 0;
    }

    public TechWorkResponseDTO editBooking(Long id, LocalDateTime dateTime) {
        TechWork service = techWorkRepository.findById(id)
                .orElseThrow(() -> new TechWorkException(HttpStatus.NOT_FOUND, "Запись не найдена"));
        service.setAppointmentTime(dateTime);
        techWorkRepository.save(service);
        return techWorkMapper.mapToDTO(service);
    }

    public TechWorkResponseDTO changeBookingInfo(Long id, TechWorkRequestDTO techWorkRequest) {
        TechWork service = techWorkRepository.findById(id)
                .orElseThrow(() -> new TechWorkException(HttpStatus.NOT_FOUND, "Запись не найдена"));
        service.setAppointmentTime(techWorkRequest.appointmentTime());
        service.setServiceTime(techWorkRequest.serviceTime());
        service.setAddress(techWorkRequest.address());
        techWorkRepository.save(service);
        return techWorkMapper.mapToDTO(service);
    }

    public TechWorkResponseDTO setDiscount(Long id, int discount) {
        TechWork service = techWorkRepository.findById(id)
                .orElseThrow(() -> new TechWorkException(HttpStatus.NOT_FOUND, "Запись не найдена"));
        service.setDiscount(discount);
        techWorkRepository.save(service);
        return techWorkMapper.mapToDTO(service);
    }

    public TechWorkResponseDTO getBookingById(Long id) {
        TechWork service = techWorkRepository.findById(id)
                .orElseThrow(() -> new TechWorkException(HttpStatus.NOT_FOUND, "Запись не найдена"));
        return techWorkMapper.mapToDTO(service);
    }

    public List<TechWorkResponseDTO> getAllBookings() {
        List<TechWork> bookings = techWorkRepository.findFutureBookings(LocalDateTime.now());
        if (bookings.isEmpty()) throw new TechWorkException(HttpStatus.NOT_FOUND, "Запись не найдена");
        return bookings.stream().map(techWorkMapper::mapToDTO).toList();
    }

    public List<TechWorkResponseDTO> getPastServices() {
        List <TechWork> pastBookings = techWorkRepository.findPastBookings(LocalDateTime.now());
        if (pastBookings.isEmpty()) throw new TechWorkException(HttpStatus.NOT_FOUND, "Запись не найдена");
        return pastBookings.stream().map(techWorkMapper::mapToDTO).toList();
    }

    public List<TechWorkResponseDTO> getBookingsByTime(LocalDateTime appointmentTime) {
        List<TechWork> bookings = techWorkRepository.findBookingsByTime(appointmentTime);
        if (bookings.isEmpty()) throw new TechWorkException(HttpStatus.NOT_FOUND, "Запись не найдена");
        return bookings.stream().map(techWorkMapper::mapToDTO).toList();
    }

    public List<BookingDwhDTO> getYesterdayBookings() {
        LocalDateTime yesterdayDate = LocalDateTime.now().minusDays(1).toLocalDate().atStartOfDay();
        LocalDateTime endDate = yesterdayDate.plusDays(1);
        List<TechWork> bookings = techWorkRepository.findCompletedBookingsInADay(yesterdayDate, endDate, COMPLETED);
        if (bookings.isEmpty()) throw new TechWorkException(HttpStatus.NOT_FOUND, "Запись не найдена");
        return bookings.stream().map(techWorkMapper::mapToDwhDTO).toList();
    }

    public float getRevenueByDate(LocalDate date){
        LocalDateTime dayStart = date.atStartOfDay();
        LocalDateTime dayEnd = date.plusDays(1).atStartOfDay();
        List<TechWork> all_bookings = techWorkRepository.findAllBookingsInADay(dayStart, dayEnd);
        if (all_bookings.isEmpty()) throw new TechWorkException(HttpStatus.NOT_FOUND, "Запись не найдена");
        float revenue = 0;
        for (TechWork booking : all_bookings) {
            revenue += booking.getServiceCategory().getCostPerHour()
                    * booking.getServiceTime()
                    * (100 - booking.getDiscount())/100;
        }
        return revenue;
    }
}
