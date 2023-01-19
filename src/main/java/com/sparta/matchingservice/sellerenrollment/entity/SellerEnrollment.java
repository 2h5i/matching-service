package com.sparta.matchingservice.sellerenrollment.entity;

import com.sparta.matchingservice.common.entity.BaseEntity;
import com.sparta.matchingservice.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SellerEnrollment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EnrollmentStatus enrollmentStatus;

    @OneToOne
    @JoinColumn(name = "customerId")
    private User customer;

    @Builder
    private SellerEnrollment(String introduce, User customer) {
        this.introduce = introduce;
        this.customer = customer;
        this.enrollmentStatus = EnrollmentStatus.WAIT;
    }

}
