package com.sparta.matchingservice.item.controller;

import com.sparta.matchingservice.item.service.ItemService;
import com.sparta.matchingservice.user.dto.ItemsResponseDto;
import com.sparta.matchingservice.user.dto.RegisterItemForm;
import com.sparta.matchingservice.user.dto.UpdateItemForm;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    // 내 판매 상품 조회
    @GetMapping("/users/seller/items")
    public List<ItemsResponseDto> getItems(Pageable pageable) {
        Profile profile = new Profile("test", "URL", "팝니다");
        return itemService.getMyItems(pageable, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).build());
    }

    // 판매 물품 등록
    @PostMapping("/items")
    public void registerItem(@RequestBody RegisterItemForm requestForm) {
        Profile profile = new Profile("test", "URL", "팝니다");
        itemService.registerItem(requestForm, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).build());
    }

    // 판매 물품 수정
    @PutMapping("/items/{itemId}")
    public void updateItem(@PathVariable Long itemId, @RequestBody UpdateItemForm requestForm) {
        Profile profile = new Profile("test", "URL", "팝니다");
        itemService.updateItem(itemId, requestForm, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).build().getId());
    }

    // 판매 물품 삭제
    @DeleteMapping("/items/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        Profile profile = new Profile("test", "URL", "팝니다");
        itemService.deleteItem(itemId, User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).build().getId());
    }

    //전체 판매 상품 조회
    //todo 판매상품조회 테스트코드 만들고 돌려보기.
    @GetMapping("/")
    public List<ItemsResponseDto> readItem(int currentPage){
        return itemService.readItem(currentPage);
    }


}
