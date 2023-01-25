package com.sparta.matchingservice.user.controller;

import com.sparta.matchingservice.security.util.UserDetailsImpl;
import com.sparta.matchingservice.user.dto.*;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.user.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SellerController {
    private final SellerService sellerService;

    // 프로필 수정
    @PatchMapping("/users/sellers/profile/{userId}")
    public void changeMyProfile(@PathVariable Long userId, @RequestBody UpdateProfileForm request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        if(userDetails.getAuthorities() == UserRole.USER) return "redirect:/users/profile/{userId}";
        sellerService.updateMyProfile(userId, request, userDetails.getUser());
    }



}
