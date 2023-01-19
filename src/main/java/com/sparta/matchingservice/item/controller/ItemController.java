package com.sparta.matchingservice.item.controller;

import com.sparta.matchingservice.item.service.ItemService;
import com.sparta.matchingservice.security.util.UserDetailsImpl;
import com.sparta.matchingservice.user.dto.ItemsResponseDto;
import com.sparta.matchingservice.user.dto.RegisterItemForm;
import com.sparta.matchingservice.user.dto.UpdateItemForm;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    // 내 판매 상품 조회
    @GetMapping("/users/seller/items")
    public List<ItemsResponseDto> getItems(Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return itemService.getMyItems(pageable, userDetails.getUser());
    }

    // 판매 물품 등록
    @PostMapping("/items")
    public void registerItem(@RequestBody RegisterItemForm requestForm, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        itemService.registerItem(requestForm, userDetails.getUser());
    }

    // 판매 물품 수정
    @PutMapping("/items/{itemId}")
    public void updateItem(@PathVariable Long itemId, @RequestBody UpdateItemForm requestForm, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        itemService.updateItem(itemId, requestForm, userDetails.getUser().getId());
    }

    // 판매 물품 삭제
    @DeleteMapping("/items/{itemId}")
    public void deleteItem(@PathVariable Long itemId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        itemService.deleteItem(itemId, userDetails.getUser().getId());
    }

    //전체 판매 상품 조회
    //todo 판매상품조회 테스트코드 만들고 돌려보기.
    @GetMapping("/items")
    public List<ItemsResponseDto> readItem(@RequestParam int currentPage){
        return itemService.readItem(currentPage);
    }


}
