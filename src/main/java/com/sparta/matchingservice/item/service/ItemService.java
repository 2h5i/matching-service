package com.sparta.matchingservice.item.service;

import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.user.dto.ItemsResponseDto;
import com.sparta.matchingservice.user.dto.RegisterItemForm;
import com.sparta.matchingservice.user.dto.UpdateItemForm;
import com.sparta.matchingservice.user.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {
    void registerItem(RegisterItemForm registerItemForm, User user);
    void updateItem(Long itemId, UpdateItemForm requestForm, Long userId);
    void deleteItem(Long itemId, Long userId);
    List<ItemsResponseDto> getMyItems(Pageable pageable, User user);
    List<ItemsResponseDto> readItem(int currentPage);
}
