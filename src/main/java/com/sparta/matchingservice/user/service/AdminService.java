package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.user.dto.ResponseCustomersAdmin;
import com.sparta.matchingservice.user.dto.ResponseSellerAdmin;
import com.sparta.matchingservice.user.dto.SearchCustomersAdmin;
import com.sparta.matchingservice.user.dto.SearchSellersAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    Page<ResponseCustomersAdmin> getCustomersAdmin(Pageable pageable, SearchCustomersAdmin searchCustomersAdmin);

    Page<ResponseSellerAdmin> getSellerAdmin(Pageable pageable, SearchSellersAdmin searchSellersAdmin);

    // TODO : SELLER ENROLLMENT로 이동
//    Page<ResponseCustomersAdmin> getSellerEnrollmentCustomersAdmin(Pageable pageable);

    void approveSellerEnrollment(Long customerId);

    void deleteSellerAuthority(Long sellerId);

}
