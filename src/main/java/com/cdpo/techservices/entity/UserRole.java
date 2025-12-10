package com.cdpo.techservices.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class UserRole {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public enum RoleType {
        ROLE_USER,
        ROLE_OPERATOR,
        ROLE_ADMIN
    }
}
