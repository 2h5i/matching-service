package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.user.dto.ResponseCustomersAdmin;
import com.sparta.matchingservice.user.dto.ResponseSellerAdmin;
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

//    private final UserRepository userRepository;

    @Transactional
    @Override
    public Page<ResponseCustomersAdmin> getCustomersAdmin(Pageable pageable) {
//        Page<User> customers = userRepository.findAll(pageable);




        return null;
    }

    @Override
    public Page<ResponseSellerAdmin> getSellerAdmin(Pageable pageable) {
        return null;
    }

    @Override
    public Page<ResponseCustomersAdmin> getSellerEnrollmentCustomersAdmin(Pageable pageable) {
        return null;
    }

    @Override
    public void approveSellerEnrollment(Long customerId) {

    }

    @Override
    public void deleteSellerAuthority(Long sellerId) {

    }
}
