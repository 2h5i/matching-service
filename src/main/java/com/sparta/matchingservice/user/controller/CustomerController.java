package com.sparta.matchingservice.user.controller;

import com.sparta.matchingservice.common.exception.CustomException;
import com.sparta.matchingservice.common.exception.ErrorCode;
import com.sparta.matchingservice.common.exception.ForbiddenException;
import com.sparta.matchingservice.security.util.UserDetailsImpl;
import com.sparta.matchingservice.sellerenrollment.dto.RequestSellerEnrollmentDto;
import com.sparta.matchingservice.sellerenrollment.dto.ResponseSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.service.SellerEnrollmentService;
import com.sparta.matchingservice.user.dto.*;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.user.service.CustomerService;
import com.sparta.matchingservice.user.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final SellerService sellerService;
    private final SellerEnrollmentService sellerEnrollmentService;


    //나의 프로필 수정 0

    @PatchMapping("/api/users/profile/{id}")
    public UserProfileResponseDto modifyUserProfile(@RequestBody ModifyUserProfileRequestDto modifyUserProfileRequestDto, @PathVariable Long id ,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        if(!id.equals(userId)) throw new CustomException(ErrorCode.FORBIDDEN_FROFILE);
        return customerService.modifyUserProfile(modifyUserProfileRequestDto, id);
    }

    // 나의 프로필 조회

    @GetMapping("/api/users/profile")
    public UserProfileResponseDto readProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        //todo 토큰에서 유저네임 꺼내서 넣기
        String userName = userDetails.getUsername();
        return customerService.readProfile(userName);
    }
    //전체 상품 목록


    //전체 판매자 목록 조회
    @GetMapping("/api/users/sellers/profile")
    public List<SellerProfileResponseDto> allSellerList(@RequestParam int currentPage) {
        return sellerService.allSellerList(currentPage);
    }


    //선택된 판매자 정보 조회

    @Secured({UserRole.Authority.USER,UserRole.Authority.SELLER,UserRole.Authority.ADMIN})
    @GetMapping("/api/users/seller/profile/{userId}")
    public SelectedSellerResponseDto selectedSeller(@PathVariable Long userId , @RequestParam int currentPage) {
        // 셀러 권한 확인하고 넘기기
        return sellerService.selectSeller(userId,currentPage);
    }



    // 판매자권한 등록 요청
//    @Secured({UserRole.Authority.USER})
    @PutMapping("/api/users/enroll-seller")
    public ResponseSellerEnrollment enrollmentSeller(@RequestBody RequestSellerEnrollmentDto requestSellerEnrollmentDto , @AuthenticationPrincipal UserDetailsImpl userDetails){
        //todo userName 빼서 넣어주기.
        User user = userDetails.getUser();
        return sellerEnrollmentService.enrollmentSeller(requestSellerEnrollmentDto,user);
    }

}
