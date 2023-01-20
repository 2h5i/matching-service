package com.sparta.matchingservice.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private HttpStatus statusCode;
    private String errorMsg;

    public ErrorResponse(HttpStatus statusCode, String errorMsg) {
        this.statusCode = statusCode;
        this.errorMsg = errorMsg;
    }

    public static ErrorResponse of(HttpStatus statusCode, String errorMsg) {
        return new ErrorResponse(statusCode , errorMsg);
    }
}
