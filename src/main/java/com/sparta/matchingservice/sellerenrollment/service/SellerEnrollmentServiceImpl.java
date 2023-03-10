package com.sparta.matchingservice.sellerenrollment.service;

import com.sparta.matchingservice.common.exception.UserNameNotFoundException;
import com.sparta.matchingservice.sellerenrollment.dto.RequestSellerEnrollmentDto;
import com.sparta.matchingservice.sellerenrollment.dto.ResponseSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.dto.SearchSellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.entity.EnrollmentStatus;
import com.sparta.matchingservice.sellerenrollment.entity.SellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.repository.SellerEnrollmentRepository;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerEnrollmentServiceImpl implements SellerEnrollmentService{

    private final SellerEnrollmentRepository sellerEnrollmentRepository;


    // 판매자권한 등록 요청
    @Override
    @Transactional
    public ResponseSellerEnrollment enrollmentSeller(RequestSellerEnrollmentDto requestSellerEnrollmentDto, User user){
        //레파지토리에서 userName으로 user객체 찾기
//        User user = userRepository.findByUserName(userName).orElseThrow(UserNameNotFoundException::new);

        SellerEnrollment sellerEnrollment = SellerEnrollment.builder()
                .introduce(requestSellerEnrollmentDto.getIntroduce())
                .customer(user)
                .build();

        sellerEnrollmentRepository.save(sellerEnrollment);
        return ResponseSellerEnrollment.of(sellerEnrollment);

    }

    //셀러 등록 고객 보기
    @Transactional
    @Override
    public Page<ResponseSellerEnrollment> getSellerEnrollmentCustomersAdmin(SearchSellerEnrollment searchSellerEnrollment,
                                                                            Pageable pageable) {

        return sellerEnrollmentRepository.getSellerEnrollmentsByCondition(searchSellerEnrollment, pageable);
    }

    //고객 등록신청하는 판매자 찾기
    @Transactional
    @Override
    public SellerEnrollment findSellerEnrollmentByCustomer(User customer) {
        return sellerEnrollmentRepository.findByCustomer(customer).orElseThrow(
                () -> new IllegalArgumentException("해당하는 등록 요청 폼이 없습니다.")
        );
    }

}
