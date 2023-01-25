package com.sparta.matchingservice.order.service;

import com.sparta.matchingservice.common.exception.IdNotFoundException;
import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.item.repository.ItemRepository;
import com.sparta.matchingservice.order.entity.Order;
import com.sparta.matchingservice.order.entity.OrderStatus;
import com.sparta.matchingservice.order.repository.OrderRepository;
import com.sparta.matchingservice.user.dto.OrderListResponseDto;
import com.sparta.matchingservice.user.dto.OrderRequestDto;
import com.sparta.matchingservice.user.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    
    //주문요청 작성
    @Override
    @Transactional
    public OrderResponseDto createOrderRequest(OrderRequestDto orderRequestDto , Long itemId, String userName){
        // 상품 아이디 확인
        Item item = itemRepository.findById(itemId).orElseThrow(IdNotFoundException::new);

        // dto에 정보담기
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .customerName(userName)
                .itemNum(itemId)
                .content(orderRequestDto.getContents())
                .orderCount(orderRequestDto.getOrderCount())
                .itemName(item.getItemName())
                .build();
        
        return orderResponseDto;
    }

    @Override
    public Page<OrderListResponseDto> getAllOrderList(Pageable pageable) {
        Page<Order> orderList = orderRepository.findAll(pageable);
        Page<OrderListResponseDto> responseDtoList = OrderListResponseDto.toDtoList(orderList);
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
