package com.cdpo.techservices.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class TechWorkException extends RuntimeException{
    private HttpStatus httpStatus;

    public TechWorkException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
