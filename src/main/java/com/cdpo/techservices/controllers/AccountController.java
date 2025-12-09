package com.cdpo.techservices.controllers;

import com.cdpo.techservices.entity.ApplicationUser;
import com.cdpo.techservices.exceptions.AccountException;
import com.cdpo.techservices.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        } catch (AccountException e) {
            throw new RuntimeException(e);
        }
        return "redirect::/next";
    }
}
