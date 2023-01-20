package com.sparta.matchingservice.order.controller;

import com.sparta.matchingservice.order.service.OrderService;
import com.sparta.matchingservice.security.util.UserDetailsImpl;
import com.sparta.matchingservice.user.dto.OrderListResponseDto;
import com.sparta.matchingservice.user.dto.OrderRequestDto;
import com.sparta.matchingservice.user.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    //주문 요청 보내기
    @PostMapping("/api/orders/{itemId}")
    public OrderResponseDto createOrderRequest(@RequestBody OrderRequestDto orderRequestDto, @PathVariable Long itemId){
        //todo 토큰에서 userName 꺼내기
        String userName = "banana";
        return orderService.createOrderRequest(orderRequestDto,itemId,userName);
    }

    // 전체 주문 요청 조회
    @GetMapping("/order-list")
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')") // 판매자와 관리자 일때만 전체 주문 요청 조회 가능
    public Page<OrderListResponseDto> getOrderList(Pageable pageable) {
        return orderService.getAllOrderList(pageable);
    }

    // 주문 요청 처리
    @PatchMapping("/orders/{orderId}")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public void matchingOrder(@PathVariable Long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        orderService.matchingOrder(orderId);
    }
}
