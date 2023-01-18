package com.sparta.matchingservice.sellerenrollment.repository;

import com.sparta.matchingservice.sellerenrollment.entity.SellerEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerEnrollmentRepository extends JpaRepository<SellerEnrollment, Long>, SellerEnrollmentCustomRepository {
}
