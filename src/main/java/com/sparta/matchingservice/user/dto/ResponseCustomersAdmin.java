package com.sparta.matchingservice.user.dto;

import com.sparta.matchingservice.user.entity.SellerEnrollment;
import com.sparta.matchingservice.user.entity.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseCustomersAdmin {

    private String userName;
    private String nickName;
    private String profileImage;
    private UserRole userRole;
    private SellerEnrollment sellerEnrollment;

}
