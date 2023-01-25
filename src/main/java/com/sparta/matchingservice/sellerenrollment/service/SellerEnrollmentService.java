package com.sparta.matchingservice.sellerenrollment.service;

import com.sparta.matchingservice.sellerenrollment.dto.RequestSellerEnrollmentDto;
import com.sparta.matchingservice.sellerenrollment.dto.ResponseSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.dto.SearchSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.entity.SellerEnrollment;
import com.sparta.matchingservice.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SellerEnrollmentService {

    Page<ResponseSellerEnrollment> getSellerEnrollmentCustomersAdmin(SearchSellerEnrollment searchSellerEnrollment,
                                                                     Pageable pageable);

    SellerEnrollment findSellerEnrollmentByCustomer(User customer);

    ResponseSellerEnrollment enrollmentSeller(RequestSellerEnrollmentDto requestSellerEnrollmentDto,User user);

}
