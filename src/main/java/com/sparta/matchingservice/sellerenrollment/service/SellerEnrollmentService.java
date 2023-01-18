package com.sparta.matchingservice.sellerenrollment.service;

import com.sparta.matchingservice.sellerenrollment.dto.ResponseSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.dto.SearchSellerEnrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SellerEnrollmentService {

    Page<ResponseSellerEnrollment> getSellerEnrollmentCustomersAdmin(SearchSellerEnrollment searchSellerEnrollment,
                                                                     Pageable pageable);

}
