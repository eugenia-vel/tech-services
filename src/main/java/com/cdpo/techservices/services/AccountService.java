package com.cdpo.techservices.services;


import com.cdpo.techservices.entity.UserRole;
import com.cdpo.techservices.repository.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cdpo.techservices.entity.ApplicationUser;
import com.cdpo.techservices.exceptions.AccountException;
import com.cdpo.techservices.repository.ApplicationUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public AccountService(ApplicationUserRepository applicationUserRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    public void register(ApplicationUser user) {
        if (applicationUserRepository.existsByUsername(user.getUsername())) {
            throw new AccountException("Username is already taken");
        }
        userRoleRepository.findByRoleType(UserRole.RoleType.USER)
                .ifPresentOrElse(user::setUserRole,
                        () -> {
                            UserRole userRole = new UserRole();
                            userRole.setRoleType(UserRole.RoleType.USER);
                            user.setUserRole(userRole);
                            userRoleRepository.save(userRole);
                        }
                );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }
}
