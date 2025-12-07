package com.cdpo.techservices.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "service_category")
public class ServiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "cost_per_hour")
    private int costPerHour;

    @Column(name="worker_name")
    private String workerName;
}
