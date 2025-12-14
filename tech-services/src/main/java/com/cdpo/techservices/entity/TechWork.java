package com.cdpo.techservices.entity;

import com.cdpo.techservices.constants.BookingStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@Entity
@Table(name="tech_work")
public class TechWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "appointment_time", nullable = false)
    private LocalDateTime appointmentTime;

    @Column(name="service_time", nullable = false)
    private Float serviceTime;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "discount", nullable = false)
    private int discount;

    @Column(name = "price")
    private int price;

    @Column(name = "status", nullable = false)
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "service_category_id")
    private ServiceCategory serviceCategory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser applicationUser;
}
