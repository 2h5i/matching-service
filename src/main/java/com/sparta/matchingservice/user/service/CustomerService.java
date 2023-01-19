package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.user.dto.ItemsResponseDto;
import com.sparta.matchingservice.user.dto.ModifyUserProfileRequestDto;
import com.sparta.matchingservice.user.dto.UserProfileResponseDto;
import com.sparta.matchingservice.user.dto.UsersignupRequestDto;

import java.util.List;


public interface CustomerService {
    UserProfileResponseDto modifyUserProfile(ModifyUserProfileRequestDto modifyUserProfileRequestDto, Long id);
    UserProfileResponseDto readProfile(String userName);


}
