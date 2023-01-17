package com.sparta.matchingservice.user.dto;

import com.sparta.matchingservice.user.entity.SellerEnrollment;
import com.sparta.matchingservice.user.entity.UserRole;
import lombok.Getter;

@Getter
public class ResponseSellerAdmin {

    private String userName;
    private String nickName;
    private String profileImage;
    private String introduce;
    private UserRole userRole;
    private SellerEnrollment sellerEnrollment;
}
