package com.sparta.matchingservice.security.controller;

import com.sparta.matchingservice.security.service.SecurityService;
import com.sparta.matchingservice.security.dto.SignupRequestDto;
import com.sparta.matchingservice.security.dto.LoginRequestDto;
import com.sparta.matchingservice.security.util.JwtUtil;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class SecurityController {
    private final SecurityService securityService;

    @PostMapping("/signup")
    public String signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        securityService.signup(signupRequestDto);
        if (signupRequestDto.isAdmin()) {
            return "ADMIN";
        } else {
            return "회원가입 성공";
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String generatedToken = securityService.login(loginRequestDto);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, generatedToken);
        return "로그인 성공";
    }
}
