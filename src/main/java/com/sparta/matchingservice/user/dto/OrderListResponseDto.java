package com.sparta.matchingservice.user.dto;

import com.sparta.matchingservice.order.entity.Order;
import com.sparta.matchingservice.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class OrderListResponseDto {
    private final Long itemId;
    private final Long customerId;
    private final OrderStatus orderStatus;
    private final Long orderCount;
    private final String content;

    public OrderListResponseDto(Order order) {
        this.itemId = order.getId();
        this.customerId = order.getCustomer().getId();
        this.orderStatus = order.getOrderStatus();
        this.orderCount = order.getOrderCount();
        this.content = order.getContent();
    }


    public static Page<OrderListResponseDto> toDtoList(Page<Order> orderList) {
        return orderList.map(OrderListResponseDto::new);
    }

}
