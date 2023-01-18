package com.sparta.matchingservice.user.controller;

import com.sparta.matchingservice.user.dto.ItemsResponseDto;
import com.sparta.matchingservice.user.dto.OrderListResponseDto;
import com.sparta.matchingservice.user.dto.RegisterItemForm;
import com.sparta.matchingservice.user.dto.UpdateItemForm;
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
        return sellerService.getMyItems(pageable, userDetails.getUser());
    }

    // 주문 요청 조회
    @GetMapping("/order-list")
    public List<OrderListResponseDto> getOrderList(Pageable pageable, @AuthenticationPrincipal UserDetails userDetails) {
        return sellerService.getAllOrderList(pageable);
    }

    // 판매 물품 등록
    @PostMapping("/items")
    public void registerItem(@RequestBody RegisterItemForm requestForm, @AuthenticationPrincipal UserDetails userDetails) {
        sellerService.registerItem(requestForm, userDetails.getUser());
    }

    // 판매 물품 수정
    @PutMapping("/items/{itemId}")
    public void updateItem(@PathVariable Long itemId, @RequestBody UpdateItemForm requestForm, @AuthenticationPrincipal UserDetails userDetails) {
        sellerService.updateItem(itemId, requestForm, userDetails.getId());
    }

    // 판매 물품 삭제
    @DeleteMapping("/items/{itemId}")
    public void deleteItem(@PathVariable Long itemId, @AuthenticationPrincipal UserDetails userDetails) {
        sellerService.deleteItem(itemId, userDetails.getId());
    }

    // 프로필 조회
    @PatchMapping("/users/sellers/profile/{userId}")
    public void changeMyProfile(@PathVariable Long userId, @RequestBody UpdateItemForm request, @AuthenticationPrincipal UserDetails userDetails) {
//        if(userDetails.getAuthorities() == UserRole.USER) return "redirect:/users/profile/{userId}";
        sellerService.updateMyProfile(userId, request, userDetails.getUser());
    }

    // 주문 요청 처리
    @PatchMapping("/orders/{orderId}")
    public void matchingOrder(@PathVariable Long orderId, @AuthenticationPrincipal UserDetails userDetails) {
        if(userDetails.getAuthorities() == UserRole.SELLER) sellerService.matchingOrder(orderId)
    }
}
