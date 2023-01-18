package com.sparta.matchingservice.item.repository;

import com.sparta.matchingservice.item.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAllByUserId(Long userId, Pageable pageable);

}
