package com.sparta.matchingservice.order.entity;

import com.sparta.matchingservice.common.entity.BaseEntity;
import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long orderCount;

    @Column(nullable = false)
    OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private User customer;

    @Builder
    public Order(String content, Long orderCount, OrderStatus orderStatus, Item item, User customer) {
        this.content = content;
        this.orderCount = orderCount;
        this.orderStatus = orderStatus;
        this.item = item;
        this.customer = customer;
    }

}
