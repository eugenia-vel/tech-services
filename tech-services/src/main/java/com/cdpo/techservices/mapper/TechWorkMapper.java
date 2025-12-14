package com.cdpo.techservices.mapper;

import com.cdpo.techservices.dto.BookingDwhDTO;
import com.cdpo.techservices.dto.TechWorkRequestDTO;
import com.cdpo.techservices.dto.TechWorkResponseDTO;
import com.cdpo.techservices.entity.TechWork;
import org.springframework.stereotype.Service;

@Service
public class TechWorkMapper {
    public TechWork mapToEntity(TechWorkRequestDTO techWorkRequestDTO) {
        return TechWork.builder()
                .appointmentTime(techWorkRequestDTO.appointmentTime())
                .serviceTime(techWorkRequestDTO.serviceTime())
                .address(techWorkRequestDTO.address())
                .discount(0)
                .build();
    }

    public TechWorkResponseDTO mapToDTO(TechWork techWork) {
        return new TechWorkResponseDTO(techWork.getServiceCategory().getServiceName(),
                techWork.getServiceCategory().getWorkerName(),
                techWork.getAppointmentTime(),
                techWork.getServiceTime(),
                techWork.getAddress(),
                techWork.getServiceCategory().getCostPerHour()*techWork.getServiceTime());
    }
    public BookingDwhDTO mapToDwhDTO(TechWork techWork) {

        return new BookingDwhDTO(techWork.getId(),
                techWork.getServiceCategory().getServiceName(),
                techWork.getServiceCategory().getWorkerName(),
                techWork.getStatus(),
                techWork.getPrice(),
                techWork.getAppointmentTime(),
                techWork.getAddress(),
                techWork.getServiceTime(),
                techWork.getApplicationUser().getId()
        );
    }
}
