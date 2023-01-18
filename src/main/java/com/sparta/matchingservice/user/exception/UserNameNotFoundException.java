package com.sparta.matchingservice.user.exception;

public class UserNameNotFoundException extends CustomException{
    public UserNameNotFoundException(){
        super(ErrorCode.USERNAME_NOT_FOUND);
    }
}
