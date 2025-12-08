package com.cdpo.techservices.controllers;

import com.cdpo.techservices.entity.ApplicationUser;
import com.cdpo.techservices.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.login.AccountException;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/registration")
    public String registrationForm(){
        return "account/registration";
    }

    @PostMapping("/registration")
    public String register(ApplicationUser user) {
        try {
            accountService.register(user);
            return "redirect::/next";
        } catch (AccountException e) {
            return "account/registration";
        }
    }
}
