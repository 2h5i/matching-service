package com.sparta.matchingservice.user.controller;

import com.sparta.matchingservice.user.dto.ResponseCustomersAdmin;
import com.sparta.matchingservice.user.dto.ResponseSellerAdmin;
import com.sparta.matchingservice.user.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/customer")
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponseCustomersAdmin> getCustomersAdmin(Pageable pageable) {
        return adminService.getCustomersAdmin(pageable);
    }

    @GetMapping("/seller")
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponseSellerAdmin> getSellerAdmin(Pageable pageable) {
        return adminService.getSellerAdmin(pageable);
    }

    @GetMapping("/want-seller")
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponseCustomersAdmin> getSellerEnrollmentCustomersAdmin(Pageable pageable) {
        return adminService.getSellerEnrollmentCustomersAdmin(pageable);
    }

    @PutMapping("/authority-approve/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public void approveSellerEnrollment(@PathVariable Long customerId) {
        adminService.approveSellerEnrollment(customerId);
    }

    @DeleteMapping("/delete-authority/{sellerId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSellerAuthority(@PathVariable Long sellerId) {
        adminService.deleteSellerAuthority(sellerId);
    }

}
