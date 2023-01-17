package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.user.dto.ResponseCustomersAdmin;
import com.sparta.matchingservice.user.dto.ResponseSellerAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    Page<ResponseCustomersAdmin> getCustomersAdmin(Pageable pageable);

    Page<ResponseSellerAdmin> getSellerAdmin(Pageable pageable);

    Page<ResponseCustomersAdmin> getSellerEnrollmentCustomersAdmin(Pageable pageable);

    void approveSellerEnrollment(Long customerId);

    void deleteSellerAuthority(Long sellerId);

}
