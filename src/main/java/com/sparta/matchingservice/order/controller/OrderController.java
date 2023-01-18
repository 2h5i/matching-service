package com.sparta.matchingservice.order.controller;

import com.sparta.matchingservice.order.service.OrderService;
import com.sparta.matchingservice.user.dto.OrderListResponseDto;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.SellerEnrollment;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    // 주문 요청 조회
    @GetMapping("/order-list")
    public List<OrderListResponseDto> getOrderList(Pageable pageable) {
        return orderService.getAllOrderList(pageable);
    }

    // 주문 요청 처리
    @PatchMapping("/orders/{orderId}")
    public void matchingOrder(@PathVariable Long orderId) {
        Profile profile = new Profile("test", "URL", "팝니다");
        User testUser = User.builder().userName("user1").profile(profile)
                .userRole(UserRole.SELLER).sellerEnrollment(SellerEnrollment.SUCCESS).build();
        if(testUser.getUserRole() == UserRole.SELLER) orderService.matchingOrder(orderId);
    }
}
