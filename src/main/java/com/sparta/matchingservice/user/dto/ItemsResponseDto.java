package com.sparta.matchingservice.user.dto;

import com.sparta.matchingservice.item.entity.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ItemsResponseDto {
    private final Long itemId;
    private final String itemName;
    private final String itemContent;
    private final Long stockCount;
    private final Long itemPrice;

    @Builder
    public ItemsResponseDto(Long itemId, String itemName, String itemContent, Long stockCount, Long itemPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemContent = itemContent;
        this.stockCount = stockCount;
        this.itemPrice = itemPrice;
    }
    public ItemsResponseDto(Item item) {
        this.itemId = item.getId();
        this.itemName = item.getItemName();
        this.itemContent = item.getItemContent();
        this.stockCount = item.getStockCount();
        this.itemPrice = item.getItemPrice();
    }
}
