package com.sparta.matchingservice.order.service;

import com.sparta.matchingservice.order.entity.Order;
import com.sparta.matchingservice.order.entity.OrderStatus;
import com.sparta.matchingservice.order.repository.OrderRepository;
import com.sparta.matchingservice.user.dto.OrderListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    @Override
    public List<OrderListResponseDto> getAllOrderList(Pageable pageable) {
        List<Order> orderList = orderRepository.findAll(pageable).getContent();
        List<OrderListResponseDto> responseDtoList = new ArrayList<>();
        orderList.stream().forEach(
                (order -> {
                    OrderListResponseDto responseDto = OrderListResponseDto.builder()
                            .itemId(order.getId())
                            .customerId(order.getCustomer().getId())
                            .orderStatus(order.getOrderStatus())
                            .orderCount(order.getOrderCount())
                            .content(order.getContent())
                            .build();
                    responseDtoList.add(responseDto);
                })
        );
        return responseDtoList;
    }

    @Override
    public void matchingOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalStateException("없는 요청입니다.")
        );
        order.changeOrderStatus(OrderStatus.SUCCESS);
        orderRepository.save(order);
    }

}
