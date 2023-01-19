package com.sparta.matchingservice.order.service;

import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.item.repository.ItemRepository;
import com.sparta.matchingservice.order.entity.Order;
import com.sparta.matchingservice.order.entity.OrderStatus;
import com.sparta.matchingservice.order.repository.OrderRepository;
import com.sparta.matchingservice.user.dto.OrderListResponseDto;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OrderServiceImplTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderServiceImpl orderService;

    @BeforeEach
    public void beforeEach() {
        Profile profile = Profile.builder().nickName("user1").profileImage("URL").build();
        User user = new User("user1", "12345", profile, UserRole.USER, false);
        userRepository.save(user);
        Item item = new Item("item1", "item1", 1L, 1000L, user);
        itemRepository.save(item);
//        orderRepository.deleteAll();
        for (int i = 0; i < 10; i++) {
            orderRepository.save(
                    Order.builder().content("test" + i)
                            .orderCount(1L).orderStatus(OrderStatus.WAIT)
                            .item(item)
                            .customer(user).build()
            );
        }
    }

    @Test
    public void 모든_주문조회() throws Exception {


        Pageable pageable = PageRequest.of(4, 3);

        List<OrderListResponseDto> allOrderList = orderService.getAllOrderList(pageable);
        allOrderList.stream().forEach(
                (order) -> System.out.println("order = " + order)
        );

        Page<Order> all = orderRepository.findAll(pageable);
        List<OrderListResponseDto> responseDtoList = new ArrayList<>();
        all.getContent().stream().forEach(
                (order -> {
                    OrderListResponseDto responseDto = OrderListResponseDto.builder()
                            .itemId(order.getId())
                            .customerId(order.getCustomer().getId())
                            .orderStatus(order.getOrderStatus())
                            .orderCount(order.getOrderCount())
                            .content(order.getContent())
                            .build();
                    responseDtoList.add(responseDto);
                }));

        System.out.println("==========================================");

        // 전체 페이지 수
        System.out.println("Total Page: " + all.getTotalPages());
        // 전체 주문 요청 갯수
        System.out.println("Total Count: " + all.getTotalElements());
        // 현재 페이지
        System.out.println("Page Number: " + all.getNumber());
        // 페이지당 데이터 갯수
        System.out.println("Page Size: " + all.getSize());
        // 다음 페이지 존재 여부
        System.out.println("next page: " + all.hasNext());

        System.out.println("==========================================");
        assertThat(allOrderList.size()).isEqualTo(responseDtoList.size());
    }

    @Test
    public void 매칭서비스() throws Exception{
        orderService.matchingOrder(2L);
        Order matchingOrder = orderRepository.findById(2L).orElseThrow(
                () -> new IllegalStateException("없는 오더")
        );
        assertThat(matchingOrder.getOrderStatus()).isEqualTo(OrderStatus.SUCCESS);
    }

}