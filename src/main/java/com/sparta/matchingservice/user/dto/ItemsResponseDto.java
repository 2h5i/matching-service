package com.sparta.matchingservice.user.dto;

import com.sparta.matchingservice.item.entity.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

public class ItemsResponseDto {
    private final Long itemId;
    private final String itemName;
    private final String itemContent;
    private final Long stockCount;
    private final Long itemPrice;

    public ItemsResponseDto(Item item) {
        this.itemId = item.getId();
        this.itemName = item.getItemName();
        this.itemContent = item.getItemContent();
        this.stockCount = item.getStockCount();
        this.itemPrice = item.getItemPrice();
    }

    public static Page<ItemsResponseDto> toDtoList(Page<Item> myItems) {
        return myItems.map(ItemsResponseDto::new);
    }

}
