package com.sparta.matchingservice.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class SecurityResponseDto {
    private String msg;
    private int statusCode;
    private boolean admin = false;

    public SecurityResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
