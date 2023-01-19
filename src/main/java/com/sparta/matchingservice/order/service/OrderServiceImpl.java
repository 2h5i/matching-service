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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
