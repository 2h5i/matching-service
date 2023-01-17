package com.sparta.matchingservice.user.controller;

import com.sparta.matchingservice.user.dto.ItemsResponseDto;
import com.sparta.matchingservice.user.dto.OrderListResponseDto;
import com.sparta.matchingservice.user.dto.RegisterItemForm;
import com.sparta.matchingservice.user.dto.UpdateItemForm;
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

    @GetMapping("/users/seller/items")
    public List<ItemsResponseDto> getItems(Pageable pageable, @AuthenticationPrincipal UserDetails userDetails) {
        return sellerService.getMyItems(pageable, userDetails.getUser());
    }

    @GetMapping("/order-list")
    public List<OrderListResponseDto> getOrderList(Pageable pageable, @AuthenticationPrincipal UserDetails userDetails) {
        return sellerService.getAllOrderList(pageable);
    }

    @PostMapping("/items")
    public void registerItem(@RequestBody RegisterItemForm requestForm, @AuthenticationPrincipal UserDetails userDetails) {
        sellerService.registerItem(requestForm, userDetails.getUser());
    }

    @PutMapping("/items/{id}")
    public void updateItem(@PathVariable Long itemId, @RequestBody UpdateItemForm requestForm, @AuthenticationPrincipal UserDetails userDetails) {
        sellerService.updateItem(itemId, requestForm, userDetails.getId());
    }

    @DeleteMapping("/items/{id}")
    public vod deleteItem(@PathVariable Long itemId, @AuthenticationPrincipal UserDetails userDetails) {
        sellerService.deleteItem(itemId, userDetails.getId());
    }
}
