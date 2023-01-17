package com.sparta.matchingservice.order.repository;

import com.sparta.matchingservice.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
