package com.sparta.matchingservice.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
public class UpdateItemForm {
    private final String itemName;
    private final String itemContent;
    private final Long stockCount;
    private final Long itemPrice;

    public UpdateItemForm(String itemName, String itemContent, Long stockCount, Long itemPrice) {
        this.itemName = Objects.requireNonNullElse(itemName, this.getItemName());
        this.itemContent = Objects.requireNonNullElse(itemContent, this.getItemContent());
        this.stockCount = Objects.requireNonNullElse(stockCount, this.getStockCount());
        this.itemPrice = Objects.requireNonNullElse(itemPrice, this.getItemPrice());
    }
}
