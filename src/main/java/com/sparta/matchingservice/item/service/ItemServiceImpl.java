package com.sparta.matchingservice.item.service;

import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.item.repository.ItemRepository;
import com.sparta.matchingservice.user.dto.ItemsResponseDto;
import com.sparta.matchingservice.user.dto.RegisterItemForm;
import com.sparta.matchingservice.user.dto.UpdateItemForm;
import com.sparta.matchingservice.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    public List<ItemsResponseDto> getMyItems(Pageable pageable, User user) {
        List<Item> myItems = itemRepository.findAllByUserId(user.getId(), pageable).getContent();
        List<ItemsResponseDto> responseDtoList = new ArrayList<>();
        myItems.stream().forEach(
                (item) -> {
                    ItemsResponseDto responseDto = ItemsResponseDto.builder()
                            .itemId(item.getId())
                            .itemName(item.getItemName())
                            .itemContent(item.getItemContent())
                            .stockCount(item.getStockCount())
                            .itemPrice(item.getItemPrice())
                            .build();
                    responseDtoList.add(responseDto);
                }
        );
        return responseDtoList;
    }

    @Override
    public void registerItem(RegisterItemForm registerItemForm, User user) {
        Item newItem = Item.builder()
                .itemName(registerItemForm.getItemName())
                .itemContent(registerItemForm.getItemContent())
                .stockCount(registerItemForm.getStockCount())
                .itemPrice(registerItemForm.getItemPrice())
                .user(user).build();
        itemRepository.save(newItem);
    }

    @Override
    public void updateItem(Long itemId, UpdateItemForm requestForm, Long userId) {
        Item findItem = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalStateException("없는 아이템입니다.")
        );
        if (findItem.getUser().getId() == userId) { // 등록한 유저만 수정 가능
            findItem.updateItem(requestForm.getItemName(), requestForm.getItemContent(),
                    requestForm.getStockCount(), requestForm.getItemPrice());
        }

    }

    @Override
    public void deleteItem(Long itemId, Long userId) {
        Item findItem = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalStateException("없는 아이템입니다.")
        );
        if (findItem.getUser().getId() == userId) { // 등록한 유저만 삭제 가능
            itemRepository.deleteById(itemId);
        }
    }
}
