package com.sparta.matchingservice.item.entity;

import com.sparta.matchingservice.common.entity.BaseEntity;
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
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String itemContent;

    @Column(nullable = false)
    private Long stockCount;

    @Column(nullable = false)
    private Long itemPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Item(String itemName, String itemContent, Long stockCount, Long itemPrice, User user) {
        this.itemName = itemName;
        this.itemContent = itemContent;
        this.stockCount = stockCount;
        this.itemPrice = itemPrice;
        this.user = user;
    }

    public void updateItem(String itemName, String itemContent, Long stockCount, Long itemPrice) {
        this.itemName = itemName;
        this.itemContent = itemContent;
        this.stockCount = stockCount;
        this.itemPrice = itemPrice;
    }
}
