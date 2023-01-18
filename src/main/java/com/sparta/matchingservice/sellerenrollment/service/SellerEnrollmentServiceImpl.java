package com.sparta.matchingservice.sellerenrollment.service;

import com.sparta.matchingservice.sellerenrollment.dto.ResponseSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.dto.SearchSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.repository.SellerEnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerEnrollmentServiceImpl implements SellerEnrollmentService{

    private final SellerEnrollmentRepository sellerEnrollmentRepository;

    @Override
    public Page<ResponseSellerEnrollment> getSellerEnrollmentCustomersAdmin(SearchSellerEnrollment searchSellerEnrollment,
                                                                            Pageable pageable) {

        sellerEnrollmentRepository.getSellerEnrollmentsByCondition(searchSellerEnrollment, pageable);

        return null;
    }
}
