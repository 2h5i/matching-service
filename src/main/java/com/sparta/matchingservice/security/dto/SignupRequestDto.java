package com.sparta.matchingservice.security.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {
    @Size(min = 4, max = 10, message = "input 4-10 characters")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Special characters are not allowed.")
    private String userName;

    @Size(min = 8, max = 15, message = "")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Special characters are not allowed.")
    private String password;
}
