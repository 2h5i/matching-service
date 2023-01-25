package com.sparta.matchingservice.common.exception;

public class ForbiddenException extends CustomException{
    public ForbiddenException() {
        super(ErrorCode.FORBIDDEN_FOR_ORDER);
    }
}