package com.sparta.matchingservice.security.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Getter
public class SignupRequestDto {
    @Size(min = 4, max = 10, message = "Name should be 4-10 characters long")
    @Pattern(regexp = "[a-zA-z0-9]+", message = "Special characters(including spaces) are not allowed.")
    private String userName;

    @Size(min = 8, max = 15, message = "Password should be 8-15 characters long")
    @Pattern(regexp = "[a-zA-z0-9]+", message = "Special characters(including spaces) are not allowed.")
    private String password;

    private String nickName;

    private String profileImage;

    private boolean admin = false;
    private String adminToken = "";

}
