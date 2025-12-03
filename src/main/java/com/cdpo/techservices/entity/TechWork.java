package com.cdpo.techservices.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="TechWork")
public class TechWork {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private long id;

    @Column(name="TechWorkName")
    private String techWorkName;

    @Column(name="WorkerName")
    private String workerName;

    @Column(name = "AppointmentTime")
    private LocalDateTime appointmentTime;

    @Column(name = "CreatedAt")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UpdatedAt")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "Address")
    private String address;
}
