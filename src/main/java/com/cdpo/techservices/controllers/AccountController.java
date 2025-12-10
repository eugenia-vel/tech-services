package com.cdpo.techservices.controllers;

import com.cdpo.techservices.entity.ApplicationUser;
import com.cdpo.techservices.entity.Token;
import com.cdpo.techservices.exceptions.AccountException;
import com.cdpo.techservices.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/login")
    public String loginForm() {
        return "account/login";
    }

    @ResponseBody
    @PostMapping("/login")
    public Token loginAccount(@RequestParam("application_user_username") String username,
                              @RequestParam("application_user_password") String password) {
        try {
            return accountService.loginAccount(username, password);
        } catch (AccountException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
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
    @PutMapping("/{id}/change")
    public ApplicationUser changeAccountInfo(@PathVariable Long id, ApplicationUser info) {
        try {
            return accountService.changeAccountInfo(id, info);
        } catch (AccountException e) {
            throw new RuntimeException(e);
        }
    }
}
