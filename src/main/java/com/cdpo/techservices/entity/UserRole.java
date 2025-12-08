package com.cdpo.techservices.entity;

import jakarta.persistence.*;
import lombok.Setter;

@Setter
@Entity
public class UserRole {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public enum RoleType {
        USER,
        OPERATOR,
        ADMIN
    }
}
