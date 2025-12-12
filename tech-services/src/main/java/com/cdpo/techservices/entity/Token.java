package com.cdpo.techservices.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {
    private String token;
    private String refreshToken;
}