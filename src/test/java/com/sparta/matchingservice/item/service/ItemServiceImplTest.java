package com.sparta.matchingservice.item.service;

import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.item.repository.ItemRepository;
import com.sparta.matchingservice.order.entity.Order;
import com.sparta.matchingservice.order.entity.OrderStatus;
import com.sparta.matchingservice.order.repository.OrderRepository;
import com.sparta.matchingservice.user.dto.ItemsResponseDto;
import com.sparta.matchingservice.user.dto.RegisterItemForm;
import com.sparta.matchingservice.user.dto.UpdateItemForm;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceImplTest {
    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @BeforeEach
    public void beforeEach() {
        Profile profile = Profile.createWithIntroduce().nickName("user1").profileImage("URL").introduce("팝니다.").build();
        User user = new User("user1", "12345", profile, UserRole.USER, true);
        userRepository.save(user);

//        orderRepository.deleteAll();
        for (int i = 0; i < 5; i++) {
            Item item = new Item("item" + i, "item" + i, 1L, 1000L, user);
            itemRepository.save(item);

            orderRepository.save(
                    Order.builder().content("test" + i)
                            .orderCount(1L).orderStatus(OrderStatus.WAIT)
                            .item(item)
                            .customer(user).build()
            );
        }

        Profile profile2 = Profile.createWithIntroduce().nickName("user1").profileImage("URL").introduce("팝니다.").build();
        User user2 = new User("user2", "12345", profile2, UserRole.USER, true);
        userRepository.save(user2);

        Item item = new Item("itemA", "itemA", 2L, 10000L, user2);
        itemRepository.save(item);
    }

    @Test
    public void 내상품_조회() {
        Pageable pageable = PageRequest.of(0, 6);
        User findUser = userRepository.findById(1L).orElseThrow(
                () -> new IllegalStateException("유저 없음")
        );
        List<ItemsResponseDto> myItems = itemService.getMyItems(pageable, findUser);
        assertThat(myItems.size()).isEqualTo(5);
    }

    @Test
    public void 상품_등록() {
        Pageable pageable = PageRequest.of(0, 10);
        RegisterItemForm registerItemForm = new RegisterItemForm("itemB", "test item", 1L, 2000L);
        User user = userRepository.findById(2L).orElseThrow(
                () -> new IllegalStateException("유저 없음")
        );
        itemService.registerItem(registerItemForm, user);

        Page<Item> user2Items = itemRepository.findAllByUserId(2L,pageable);
        assertThat(user2Items.getContent().size()).isEqualTo(2);
    }

    @Test
    public void 상품_수정() {
        UpdateItemForm updateItemForm = new UpdateItemForm("itemC", "update item", 2L, 33000L);
        User user = userRepository.findById(2L).orElseThrow(
                () -> new IllegalStateException("유저 없음")
        );
        itemService.updateItem(6L, updateItemForm, 2L);

        Optional<Item> updateItem = itemRepository.findById(6L);
        assertThat(updateItem.get().getItemName()).isEqualTo("itemC");
    }

    @Test
    public void 상품_삭제() {
        itemService.deleteItem(1L, 1L);
        User user = userRepository.findById(1L).orElseThrow(
                () -> new IllegalStateException("회원 없음")
        );
//        List<ItemsResponseDto> myItems = itemService.getMyItems(PageRequest.of(0, 10), user);
        List<ItemsResponseDto> responseDtoList = itemService.readItem(0);
        assertThat(responseDtoList.size()).isEqualTo(5);
    }
}