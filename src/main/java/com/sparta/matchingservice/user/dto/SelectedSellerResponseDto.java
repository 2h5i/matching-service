package com.sparta.matchingservice.user.dto;

import com.sparta.matchingservice.item.entity.Item;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SelectedSellerResponseDto {
    private final String userName;
    private final String introduce;

    private List<ItemsResponseDto>  sellerItem = new ArrayList<>();

    public SelectedSellerResponseDto(String userName,String introduce , List<Item> sellerItem){
        this.userName = userName;
        this.introduce = introduce;
        for(Item item:sellerItem){
            this.sellerItem.add(new ItemsResponseDto(item));
        }
    }
}
