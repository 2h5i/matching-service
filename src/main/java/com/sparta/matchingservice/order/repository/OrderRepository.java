package com.sparta.matchingservice.order.repository;

import com.sparta.matchingservice.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllOrderByCreatedAtDESC(Pageable pageable);
}
