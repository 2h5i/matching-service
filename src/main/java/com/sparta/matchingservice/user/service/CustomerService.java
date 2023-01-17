package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.user.dto.ModifyUserProfileRequestDto;
import com.sparta.matchingservice.user.dto.UserProfileResponseDto;
import com.sparta.matchingservice.user.dto.UsersignupRequestDto;


public interface CustomerService {
    UserProfileResponseDto modifyUserProfile(ModifyUserProfileRequestDto modifyUserProfileRequestDto, Long id);
//    void signup();

}
