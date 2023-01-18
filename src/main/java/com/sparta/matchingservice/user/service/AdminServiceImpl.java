package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.user.dto.ResponseCustomersAdmin;
import com.sparta.matchingservice.user.dto.ResponseSellerAdmin;
import com.sparta.matchingservice.user.dto.SearchCustomersAdmin;
import com.sparta.matchingservice.user.dto.SearchSellersAdmin;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;

    @Transactional
    @Override
    public Page<ResponseCustomersAdmin> getCustomersAdmin(Pageable pageable, SearchCustomersAdmin searchCustomersAdmin) {

        return userRepository.getCustomersByCondition(pageable, searchCustomersAdmin);
    }

    @Transactional
    @Override
    public Page<ResponseSellerAdmin> getSellerAdmin(Pageable pageable, SearchSellersAdmin searchSellersAdmin) {
        return userRepository.getSellersByCondition(pageable, searchSellersAdmin);
    }

    // TODO : SellerEnrollment로 이동
//    @Override
//    public Page<ResponseCustomersAdmin> getSellerEnrollmentCustomersAdmin(Pageable pageable) {
//        return null;
//    }

    @Transactional
    @Override
    public void approveSellerEnrollment(Long customerId) {

    }

    @Transactional
    @Override
    public void deleteSellerAuthority(Long sellerId) {
        User user = userRepository.findById(sellerId).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        user.changeAuthoritySellerToCustomer();
        userRepository.saveAndFlush(user);
    }
}
