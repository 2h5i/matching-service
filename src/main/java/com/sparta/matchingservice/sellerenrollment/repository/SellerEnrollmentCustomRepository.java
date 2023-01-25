package com.sparta.matchingservice.sellerenrollment.repository;

import com.sparta.matchingservice.sellerenrollment.dto.ResponseSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.dto.SearchSellerEnrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SellerEnrollmentCustomRepository {

    Page<ResponseSellerEnrollment> getSellerEnrollmentsByCondition(SearchSellerEnrollment searchSellerEnrollment,
                                                                   Pageable pageable);

}
