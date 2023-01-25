package com.sparta.matchingservice.common.exception;

import java.time.LocalDateTime;

public class CustomException extends RuntimeException {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String message;

    public CustomException(ErrorCode errorCode) {
        this.status = errorCode.getStatus().value();
        this.message = errorCode.getMessage();
    }
}
