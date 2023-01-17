package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.user.dto.ItemsResponseDto;
import com.sparta.matchingservice.user.dto.OrderListResponseDto;
import com.sparta.matchingservice.user.dto.RegisterItemForm;
import com.sparta.matchingservice.user.dto.UpdateItemForm;
import com.sparta.matchingservice.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface SellerService {
    List<ItemsResponseDto> getMyItems(Pageable pageable, User user);

    List<OrderListResponseDto> getAllOrderList(Pageable pageable);

    void registerItem(RegisterItemForm registerItemForm, User user);

    void updateItem(Long itemId, UpdateItemForm requestForm, Long userId);

    void deleteItem(Long itemId, Long userId);
}
