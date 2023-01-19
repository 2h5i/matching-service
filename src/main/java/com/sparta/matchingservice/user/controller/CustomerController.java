package com.sparta.matchingservice.user.controller;

import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.user.dto.*;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.user.service.CustomerService;
import com.sparta.matchingservice.user.service.CustomerServiceImpl;
import com.sparta.matchingservice.user.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final SellerService sellerService;


    //나의 프로필 수정
    @PatchMapping("/api/users/profile/{id}")
    public UserProfileResponseDto modifyUserProfile(@RequestBody ModifyUserProfileRequestDto modifyUserProfileRequestDto, @PathVariable Long id) {

        return customerService.modifyUserProfile(modifyUserProfileRequestDto, id);
    }

    // 나의 프로필 조회

    @GetMapping("/api/users/profile")
    public UserProfileResponseDto readProfile() {
        //todo 토큰에서 유저네임 꺼내서 넣기
        String userName = "banana";
        return customerService.readProfile(userName);
    }
    //전체 상품 목록

    //전체 판매자 목록
    @GetMapping("/api/users/sellers/profile")
    public List<SellerProfileResponseDto> allSellerList(@RequestParam int currentPage) {
        return sellerService.allSellerList(currentPage);
    }

    //선택된 판매자 정보 조회

    @GetMapping("/api/users/seller/profile/{userId}")
    public SelectedSellerResponseDto selectedSeller(@PathVariable Long userId , @RequestParam int currentPage) {
        // 셀러 권한 확인하고 넘기기
        return sellerService.selectSeller(userId,currentPage);
    }


    // 판매자 주문 요청폼 작성






    // 판매자권한 등록 요청


}
