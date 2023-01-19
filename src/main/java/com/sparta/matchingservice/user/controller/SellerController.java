package com.sparta.matchingservice.user.controller;

import com.sparta.matchingservice.user.dto.*;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.user.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SellerController {
    private final SellerService sellerService;

    // 프로필 수정
    @PatchMapping("/users/sellers/profile/{userId}")
    public void changeMyProfile(@PathVariable Long userId, @RequestBody UpdateProfileForm request) {
//        if(userDetails.getAuthorities() == UserRole.USER) return "redirect:/users/profile/{userId}";
        Profile profile = new Profile("test", "URL", "팝니다");
        sellerService.updateMyProfile(userId, request, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).build());
    }


}
