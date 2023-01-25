package com.sparta.matchingservice.sellerenrollment.repository;

import com.sparta.matchingservice.sellerenrollment.entity.SellerEnrollment;
import com.sparta.matchingservice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerEnrollmentRepository extends JpaRepository<SellerEnrollment, Long>, SellerEnrollmentCustomRepository {

    Optional<SellerEnrollment> findByCustomer(User customer);
}
