package com.sparta.matchingservice.user.dto;

import lombok.Getter;

@Getter
public class OrderRequestDto {

    private String contents;
    private Long orderCount;

    public OrderRequestDto(String contents,Long stockCount){
        this.contents = contents;
        this.orderCount = orderCount;
    }

}
