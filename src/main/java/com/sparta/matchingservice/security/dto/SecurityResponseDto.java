package com.sparta.matchingservice.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SecurityResponseDto {
    private String msg;
    private int statusCode;
    private boolean admin = false;

    public SecurityResponseDto(String msg, int statusCode, boolean admin) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.admin = admin;
    }
}
