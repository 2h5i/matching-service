package com.sparta.matchingservice.user.dto;

import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.SellerEnrollment;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
public class UserProfileResponseDto {

    private Long id;

    private String userName;

    private Profile profile;

    private UserRole userRole;

    private SellerEnrollment sellerEnrollment;

    public UserProfileResponseDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.profile = user.getProfile();
        this.userRole = user.getUserRole();
        this.sellerEnrollment = user.getSellerEnrollment();
    }
}
