package com.sparta.matchingservice.user.dto;

import com.sparta.matchingservice.order.entity.OrderStatus;
import lombok.Builder;

public class OrderListResponseDto {
    private final Long itemId;
    private final Long customerId;
    private final OrderStatus orderStatus;
    private final Long orderCount;
    private final String content;

    @Builder
    public OrderListResponseDto(Long itemId, Long customerId, OrderStatus orderStatus, Long orderCount, String content) {
        this.itemId = itemId;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.orderCount = orderCount;
        this.content = content;
    }
}
