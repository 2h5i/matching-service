package com.sparta.matchingservice.sellerenrollment.controller;

import com.sparta.matchingservice.sellerenrollment.dto.ResponseSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.dto.SearchSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.service.SellerEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller-enrollment")
public class SellerEnrollmentController {

    private final SellerEnrollmentService sellerEnrollmentService;



    //판매자 권한 요청 보기
    @GetMapping("/want-seller")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<ResponseSellerEnrollment> getSellerEnrollmentCustomersAdmin(SearchSellerEnrollment searchSellerEnrollment,
                                                                            Pageable pageable) {

        return sellerEnrollmentService.getSellerEnrollmentCustomersAdmin(searchSellerEnrollment, pageable);
    }
}
