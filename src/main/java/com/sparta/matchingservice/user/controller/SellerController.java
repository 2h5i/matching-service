package com.sparta.matchingservice.user.controller;

import com.sparta.matchingservice.user.dto.*;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.SellerEnrollment;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.user.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SellerController {
    private final SellerService sellerService;

    // 내 판매 상품 조회
    @GetMapping("/users/seller/items")
    public List<ItemsResponseDto> getItems(Pageable pageable, @AuthenticationPrincipal UserDetails userDetails) {
        Profile profile = new Profile("test", "URL", "팝니다");
        return sellerService.getMyItems(pageable, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).sellerEnrollment(SellerEnrollment.SUCCESS).build());
    }

    // 주문 요청 조회
    @GetMapping("/order-list")
    public List<OrderListResponseDto> getOrderList(Pageable pageable) {
        return sellerService.getAllOrderList(pageable);
    }

    // 판매 물품 등록
    @PostMapping("/items")
    public void registerItem(@RequestBody RegisterItemForm requestForm) {
        Profile profile = new Profile("test", "URL", "팝니다");
        sellerService.registerItem(requestForm, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).sellerEnrollment(SellerEnrollment.SUCCESS).build());
    }

    // 판매 물품 수정
    @PutMapping("/items/{itemId}")
    public void updateItem(@PathVariable Long itemId, @RequestBody UpdateItemForm requestForm, @AuthenticationPrincipal UserDetails userDetails) {
        Profile profile = new Profile("test", "URL", "팝니다");
        sellerService.updateItem(itemId, requestForm, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).sellerEnrollment(SellerEnrollment.SUCCESS).build().getId());
    }

    // 판매 물품 삭제
    @DeleteMapping("/items/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        Profile profile = new Profile("test", "URL", "팝니다");
        sellerService.deleteItem(itemId, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).sellerEnrollment(SellerEnrollment.SUCCESS).build().getId());
    }

    // 프로필 수정
    @PatchMapping("/users/sellers/profile/{userId}")
    public void changeMyProfile(@PathVariable Long userId, @RequestBody UpdateProfileForm request) {
//        if(userDetails.getAuthorities() == UserRole.USER) return "redirect:/users/profile/{userId}";
        Profile profile = new Profile("test", "URL", "팝니다");
        sellerService.updateMyProfile(userId, request, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).sellerEnrollment(SellerEnrollment.SUCCESS).build());
    }

    // 주문 요청 처리
    @PatchMapping("/orders/{orderId}")
    public void matchingOrder(@PathVariable Long orderId) {
        Profile profile = new Profile("test", "URL", "팝니다");
        User testUser = User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).sellerEnrollment(SellerEnrollment.SUCCESS).build();
        if(testUser.getUserRole() == UserRole.SELLER) sellerService.matchingOrder(orderId);
    }
}
