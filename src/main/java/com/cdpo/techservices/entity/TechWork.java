package com.cdpo.techservices.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="tech_work")
public class TechWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="service_name", nullable = false)
    private String serviceName;

    @Column(name="worker_name")
    private String workerName;

    @Column(name = "appointment_time", nullable = false)
    private LocalDateTime appointmentTime;

    @Column(name="service_time", nullable = false)
    private Float serviceTime;

    @Column(name="cost_per_hour", nullable = false)
    private int costPerHour;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "address", nullable = false)
    private String address;
}
