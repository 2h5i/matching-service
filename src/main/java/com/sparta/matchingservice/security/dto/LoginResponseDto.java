package com.sparta.matchingservice.security.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final String msg;

    private final int statusCode;

    public LoginResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
