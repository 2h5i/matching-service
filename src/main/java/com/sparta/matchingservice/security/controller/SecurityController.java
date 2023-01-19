package com.sparta.matchingservice.security.controller;

import com.sparta.matchingservice.security.dto.LoginResponseDto;
import com.sparta.matchingservice.security.service.SecurityService;
import com.sparta.matchingservice.security.dto.SignupRequestDto;
import com.sparta.matchingservice.security.dto.LoginRequestDto;
import com.sparta.matchingservice.security.dto.SecurityResponseDto;
import com.sparta.matchingservice.security.util.JwtUtil;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class SecurityController {
    private final SecurityService securityService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public SecurityResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return securityService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String generatedToken = securityService.login(loginRequestDto);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, generatedToken);
        return new LoginResponseDto("로그인 완료",200);
    }
}