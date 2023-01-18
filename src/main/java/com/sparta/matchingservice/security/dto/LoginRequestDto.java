package com.sparta.matchingservice.security.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    private String userName;
    private String password;
}
