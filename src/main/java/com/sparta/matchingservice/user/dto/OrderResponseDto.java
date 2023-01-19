package com.sparta.matchingservice.user.dto;

import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.order.entity.OrderStatus;
import com.sparta.matchingservice.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderResponseDto {

    private String content;
    private Long itemNum;
    private String itemName;
    private String customerName;
    private Long orderCount;

    @Builder
    public OrderResponseDto(String content,Long itemNum, String itemName, String customerName,Long orderCount){
        this.content = content;
        this.customerName = customerName;
        this.itemNum = itemNum;
        this.itemName = itemName;
        this.orderCount = orderCount;
    }


}
