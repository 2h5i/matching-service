package com.sparta.matchingservice.user.exception;

public class IdNotFoundException extends CustomException{
    public IdNotFoundException(){
        super(ErrorCode.ID_NOT_FOUND);
    }
}
