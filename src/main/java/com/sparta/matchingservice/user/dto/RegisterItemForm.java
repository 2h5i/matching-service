package com.sparta.matchingservice.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterItemForm {
    private final String itemName;
    private final String itemContent;
    private final Long stockCount;
    private final Long itemPrice;

}
