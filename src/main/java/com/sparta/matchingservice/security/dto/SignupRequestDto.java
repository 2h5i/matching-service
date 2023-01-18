package com.sparta.matchingservice.security.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {
    @Size(min = 4, max = 10, message = "User name should be 4-10 characters long")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Special characters are not allowed.")
    private String userName;

    @Size(min = 8, max = 15, message = "Password should be 8-15 characters long")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Special characters are not allowed.")
    private String password;
}
