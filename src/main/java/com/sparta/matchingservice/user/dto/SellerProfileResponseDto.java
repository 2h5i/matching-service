package com.sparta.matchingservice.user.dto;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class SellerProfileResponseDto {

    private String userName;
    private String introduce;

    public SellerProfileResponseDto(String userName,String introduce){
        this.userName = userName;
        this.introduce = introduce;
    }


}
