package com.sparta.matchingservice.sellerenrollment.dto;

import com.sparta.matchingservice.sellerenrollment.entity.EnrollmentStatus;
import com.sparta.matchingservice.sellerenrollment.entity.SellerEnrollment;
import com.sparta.matchingservice.user.dto.ResponseCustomersAdmin;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseSellerEnrollment {

    private ResponseCustomersAdmin customer;
    private String introduce;
    private EnrollmentStatus enrollmentStatus;

    private ResponseSellerEnrollment(SellerEnrollment sellerEnrollment) {
        this.customer = ResponseCustomersAdmin.of(sellerEnrollment.getCustomer());
        this.introduce = sellerEnrollment.getIntroduce();
        this.enrollmentStatus = sellerEnrollment.getEnrollmentStatus();
    }

    public static ResponseSellerEnrollment of(SellerEnrollment sellerEnrollment) {
        return new ResponseSellerEnrollment(sellerEnrollment);
    }

    public static List<ResponseSellerEnrollment> of(List<SellerEnrollment> sellerEnrollments) {
        return sellerEnrollments.stream().map(ResponseSellerEnrollment::of).collect(Collectors.toList());
    }

}
