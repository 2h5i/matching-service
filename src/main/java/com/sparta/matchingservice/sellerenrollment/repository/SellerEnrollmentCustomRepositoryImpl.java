package com.sparta.matchingservice.sellerenrollment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.matchingservice.sellerenrollment.dto.ResponseSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.dto.SearchSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.entity.EnrollmentStatus;
import com.sparta.matchingservice.sellerenrollment.entity.SellerEnrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.sparta.matchingservice.sellerenrollment.entity.QSellerEnrollment.sellerEnrollment;

@Repository
public class SellerEnrollmentCustomRepositoryImpl extends QuerydslRepositorySupport implements SellerEnrollmentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public SellerEnrollmentCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(SellerEnrollment.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    private BooleanExpression searchByCustomerName(String customerName) {

        if(!ObjectUtils.isEmpty(customerName)) {
            return sellerEnrollment.customer.userName.contains(customerName);
        }
        return null;
    }

    private BooleanExpression searchByIntroduce(String introduce) {

        if(!ObjectUtils.isEmpty(introduce)){
            return sellerEnrollment.introduce.contains(introduce);
        }
        return null;
    }

    private BooleanExpression searchByEnrollmentStatus(EnrollmentStatus enrollmentStatus) {

        if(!ObjectUtils.isEmpty(enrollmentStatus)){
            return sellerEnrollment.enrollmentStatus.eq(enrollmentStatus);
        }
        return null;
    }

    @Override
    public Page<ResponseSellerEnrollment> getSellerEnrollmentsByCondition(SearchSellerEnrollment searchSellerEnrollment,
                                                                          Pageable pageable) {
        List<SellerEnrollment> findSellerEnrollments = jpaQueryFactory.selectFrom(sellerEnrollment)
                .where(
                        searchByCustomerName(searchSellerEnrollment.getCustomerName()),
                        searchByIntroduce(searchSellerEnrollment.getIntroduce()),
                        searchByEnrollmentStatus(searchSellerEnrollment.getEnrollmentStatus())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(ResponseSellerEnrollment.of(findSellerEnrollments), pageable, findSellerEnrollments.size());
    }
}
