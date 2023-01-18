package com.sparta.matchingservice.item.controller;

import com.sparta.matchingservice.item.service.ItemService;
import com.sparta.matchingservice.user.dto.ItemsResponseDto;
import com.sparta.matchingservice.user.dto.RegisterItemForm;
import com.sparta.matchingservice.user.dto.UpdateItemForm;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.SellerEnrollment;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    // 내 판매 상품 조회
    @GetMapping("/users/seller/items")
    public List<ItemsResponseDto> getItems(Pageable pageable, @AuthenticationPrincipal UserDetails userDetails) {
        Profile profile = new Profile("test", "URL", "팝니다");
        return itemService.getMyItems(pageable, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).sellerEnrollment(SellerEnrollment.SUCCESS).build());
    }

    // 판매 물품 등록
    @PostMapping("/items")
    public void registerItem(@RequestBody RegisterItemForm requestForm) {
        Profile profile = new Profile("test", "URL", "팝니다");
        itemService.registerItem(requestForm, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).sellerEnrollment(SellerEnrollment.SUCCESS).build());
    }

    // 판매 물품 수정
    @PutMapping("/items/{itemId}")
    public void updateItem(@PathVariable Long itemId, @RequestBody UpdateItemForm requestForm, @AuthenticationPrincipal UserDetails userDetails) {
        Profile profile = new Profile("test", "URL", "팝니다");
        itemService.updateItem(itemId, requestForm, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).sellerEnrollment(SellerEnrollment.SUCCESS).build().getId());
    }

    // 판매 물품 삭제
    @DeleteMapping("/items/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        Profile profile = new Profile("test", "URL", "팝니다");
        itemService.deleteItem(itemId, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).sellerEnrollment(SellerEnrollment.SUCCESS).build().getId());
    }
}
