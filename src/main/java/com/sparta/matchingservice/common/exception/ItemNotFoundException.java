package com.sparta.matchingservice.common.exception;

public class ItemNotFoundException extends CustomException{
    public ItemNotFoundException(){
        super(ErrorCode.ITEM_NOT_FOUND);
    }
}
