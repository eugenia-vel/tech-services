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

    public AccountService(ApplicationUserRepository applicationUserRepository,
                          PasswordEncoder passwordEncoder,
                          UserRoleRepository userRoleRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    public void register(ApplicationUser user) throws AccountException {
        if (applicationUserRepository.existsByUsername(user.getUsername())) {
            throw new AccountException("Username is already taken");
        }
        userRoleRepository.findByRoleType(UserRole.RoleType.ROLE_USER)
                .ifPresentOrElse(user::setUserRole,
                        () -> {
                            UserRole userRole = new UserRole();
                            userRole.setRoleType(UserRole.RoleType.ROLE_USER);
                            user.setUserRole(userRole);
                            userRoleRepository.save(userRole);
                        }
                );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

    public ApplicationUser changeAccountInfo(Long id, ApplicationUser info) throws AccountException {
        if (applicationUserRepository.existsByUsername(info.getUsername())) {
            throw new AccountException("User with new username" + info.getUsername() + "already exists");
        }
        ApplicationUser applicationUser = applicationUserRepository.findById(id)
                .orElseThrow(() -> new AccountException("User with this username doesn't exist"));
        applicationUser.setUsernameIfNotNull(info.getUsername());
        applicationUser.setPasswordIfNotNull(passwordEncoder.encode(info.getPassword()));
        applicationUserRepository.save(applicationUser);
        return applicationUser;
    }
}
