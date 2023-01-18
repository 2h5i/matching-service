package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.item.repository.ItemRepository;
import com.sparta.matchingservice.order.entity.Order;
import com.sparta.matchingservice.order.entity.OrderStatus;
import com.sparta.matchingservice.order.repository.OrderRepository;
import com.sparta.matchingservice.user.dto.*;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService{
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public List<ItemsResponseDto> getMyItems(Pageable pageable, User user) {
        List<Item> myItems = itemRepository.findAllByUserId(user.getId(), pageable).getContent();
        List<ItemsResponseDto> responseDtoList = new ArrayList<>();
        myItems.stream().forEach(
                (item) -> {
                    ItemsResponseDto responseDto = ItemsResponseDto.builder()
                            .itemId(item.getId())
                            .itemName(item.getItemName())
                            .itemContent(item.getItemContent())
                            .stockCount(item.getStockCount())
                            .itemPrice(item.getItemPrice())
                            .build();
                    responseDtoList.add(responseDto);
                }
        );
        return responseDtoList;
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
    public void registerItem(RegisterItemForm registerItemForm, User user) {
        Item newItem = Item.builder()
                .itemName(registerItemForm.getItemName())
                .itemContent(registerItemForm.getItemContent())
                .stockCount(registerItemForm.getStockCount())
                .itemPrice(registerItemForm.getItemPrice())
                .user(user).build();
        itemRepository.save(newItem);
    }

    @Override
    public void updateItem(Long itemId, UpdateItemForm requestForm, Long userId) {
        Item findItem = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalStateException("없는 아이템입니다.")
        );
        if (findItem.getUser().getId() == userId) { // 등록한 유저만 수정 가능
           findItem.updateItem(requestForm.getItemName(), requestForm.getItemContent(),
                   requestForm.getStockCount(), requestForm.getItemPrice());
        }

    }

    @Override
    public void deleteItem(Long itemId, Long userId) {
        Item findItem = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalStateException("없는 아이템입니다.")
        );
        if (findItem.getUser().getId() == userId) {
            itemRepository.deleteById(itemId);
        }
    }

    @Override
    public void updateMyProfile(Long userId, UpdateProfileForm updateProfileForm, User user) {
        if (user.getId() == userId) {
            // 시큐리티에서 존재하는 유저인지 체크해서 줄 것
            User findUser = userRepository.findById(userId).get();
            Profile findUserProfile = findUser.getProfile();
            findUserProfile.updateSellerProfile(updateProfileForm.getNickName(),
                    updateProfileForm.getProfileImage(), updateProfileForm.getIntroduce());
            userRepository.save(findUser);
        }
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
