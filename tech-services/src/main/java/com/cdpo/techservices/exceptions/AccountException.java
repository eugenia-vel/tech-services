package com.cdpo.techservices.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AccountException extends Exception{

    public AccountException(String message) {
        super(message);
    }
}
