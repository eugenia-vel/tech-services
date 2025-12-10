package com.cdpo.techservices.services;


import com.cdpo.techservices.entity.UserRole;
import com.cdpo.techservices.repository.UserRoleRepository;
import com.nimbusds.jose.JOSEException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdpo.techservices.entity.ApplicationUser;
import com.cdpo.techservices.exceptions.AccountException;
import com.cdpo.techservices.repository.ApplicationUserRepository;
import com.cdpo.techservices.entity.Token;
//import com.cdpo.techservices.services.JwtSecurityService;


@Service
public class AccountService {

    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final JwtSecurityService jwtSecurityService;
    private final AuthenticationManager authenticationManager;

    public AccountService(ApplicationUserRepository applicationUserRepository,
                          PasswordEncoder passwordEncoder,
                          UserRoleRepository userRoleRepository,
                          JwtSecurityService jwtSecurityService,
                          AuthenticationManager authenticationManager) {
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.jwtSecurityService = jwtSecurityService;
        this.authenticationManager = authenticationManager;
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

    public Token loginAccount(String username, String password) throws AccountException {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Token token = new Token();
        try {
            token.setToken(jwtSecurityService.generateToken((UserDetails) authentication.getPrincipal()));
            token.setRefreshToken(jwtSecurityService.generateRefreshToken());
        } catch (JOSEException e) {
            throw new AccountException("Token cannot ne created: " + e.getMessage());
        }
        return token;
    }
}
