package com.sparta.matchingservice.user.controller;

import com.sparta.matchingservice.user.dto.ModifyUserProfileRequestDto;
import com.sparta.matchingservice.user.dto.UserProfileResponseDto;
import com.sparta.matchingservice.user.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    CustomerServiceImpl customerService;

    //나의 프로필 수정
    @PatchMapping("/api/users/profile/{id}")
    public UserProfileResponseDto modifyUserProfile(@RequestBody ModifyUserProfileRequestDto modifyUserProfileRequestDto, @PathVariable Long id){

        return customerService.modifyUserProfile(modifyUserProfileRequestDto,id);
    }


    // 나의 프로필 조회

    //전체 판매상품 목록 조회

    //전체 판매자 목록

    //선택된 판매자 정보 조회

    // 판매자 요청폼 작성

    // 판매자 등록 요청
}
