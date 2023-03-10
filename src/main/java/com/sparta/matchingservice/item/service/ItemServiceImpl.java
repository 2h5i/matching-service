package com.sparta.matchingservice.item.service;

import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.item.repository.ItemRepository;
import com.sparta.matchingservice.user.dto.ItemsResponseDto;
import com.sparta.matchingservice.user.dto.RegisterItemForm;
import com.sparta.matchingservice.user.dto.UpdateItemForm;
import com.sparta.matchingservice.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ItemsResponseDto> getMyItems(Pageable pageable, User user) {
        Page<Item> myItems = itemRepository.findAllByUserIdAndIsAvailable(user.getId(),true, pageable);
        Page<ItemsResponseDto> responseDtoList = ItemsResponseDto.toDtoList(myItems);
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
            itemRepository.save(findItem);
        }

    }

    @Override
    public void deleteItem(Long itemId, Long userId) {
        Item findItem = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalStateException("없는 아이템입니다.")
        );
        if (findItem.getUser().getId() == userId) { // 등록한 유저만 삭제 가능
            findItem.unavailableItem();
            itemRepository.save(findItem);
        }
    }


    //전체 상품 조회
    @Override
    @Transactional(readOnly = true)
    public List<ItemsResponseDto> readItem(int currentPage){
        if(currentPage==0) currentPage=1;
        Page<Item> itemPage = itemRepository.findAll(PageRequest.of(currentPage-1,10,Sort.by("id").descending()));

        List<ItemsResponseDto> itemsResponseDtos = new ArrayList<>();

        for(Item item:itemPage){
            if(item.getIsAvailable()) itemsResponseDtos.add(new ItemsResponseDto(item));
        }

        return itemsResponseDtos;

    }

}
