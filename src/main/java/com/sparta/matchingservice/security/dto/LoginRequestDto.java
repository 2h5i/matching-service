package com.sparta.matchingservice.security.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String userName;
    private String password;
}
