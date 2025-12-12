package com.cdpo.techservices.repository;

import java.util.Optional;

import com.cdpo.techservices.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findByRoleType(UserRole.RoleType roleType);
}