package com.sparta.matchingservice.common.exception;

public class UserNameNotFoundException extends CustomException{
    public UserNameNotFoundException(){
        super(ErrorCode.USERNAME_NOT_FOUND);
    }
}
