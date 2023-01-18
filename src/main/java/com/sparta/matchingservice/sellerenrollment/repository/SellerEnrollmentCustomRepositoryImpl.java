package com.sparta.matchingservice.sellerenrollment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.matchingservice.sellerenrollment.dto.ResponseSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.dto.SearchSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.entity.SellerEnrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.sparta.matchingservice.sellerenrollment.entity.QSellerEnrollment.sellerEnrollment;
import static com.sparta.matchingservice.user.entity.QUser.user;

@Repository
public class SellerEnrollmentCustomRepositoryImpl extends QuerydslRepositorySupport implements SellerEnrollmentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public SellerEnrollmentCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(SellerEnrollment.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<ResponseSellerEnrollment> getSellerEnrollmentsByCondition(SearchSellerEnrollment searchSellerEnrollment,
                                                                          Pageable pageable) {
        // TODO : 쿼리문 짜기
        return null;
    }
}
