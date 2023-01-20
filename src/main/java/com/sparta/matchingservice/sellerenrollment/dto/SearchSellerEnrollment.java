package com.sparta.matchingservice.sellerenrollment.dto;

import com.sparta.matchingservice.sellerenrollment.entity.EnrollmentStatus;
import lombok.Getter;

@Getter
public class SearchSellerEnrollment {

    private String customerName;
    private String introduce;
    private EnrollmentStatus enrollmentStatus;

    private SearchSellerEnrollment(String customerName, String introduce, EnrollmentStatus enrollmentStatus) {
        this.customerName = customerName;
        this.introduce = introduce;
        this.enrollmentStatus = enrollmentStatus;
    }

}
