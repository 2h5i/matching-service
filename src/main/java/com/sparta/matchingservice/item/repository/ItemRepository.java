package com.sparta.matchingservice.item.repository;

import com.sparta.matchingservice.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
