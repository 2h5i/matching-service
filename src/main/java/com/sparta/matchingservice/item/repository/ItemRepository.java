package com.sparta.matchingservice.item.repository;

import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAllByUserId(Long userId, Pageable pageable);
    Optional<Item> findById(Long userId);
    List<Item> findByUser(User user);


    Page<Item> findByUserId(Long userId, Pageable pageable);
}
