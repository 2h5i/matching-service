package com.sparta.matchingservice.order.service;

import com.sparta.matchingservice.user.dto.OrderListResponseDto;
import com.sparta.matchingservice.user.dto.OrderRequestDto;
import com.sparta.matchingservice.user.dto.OrderResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    List<OrderListResponseDto> getAllOrderList(Pageable pageable);
    void matchingOrder(Long orderId);

    OrderResponseDto createOrderRequest(OrderRequestDto orderRequestDto, Long itemId, String userName);
}
