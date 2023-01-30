package com.sparta.matchingservice.security.service;

import java.util.Optional;

import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.user.repository.UserRepository;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.security.util.JwtUtil;
import com.sparta.matchingservice.security.dto.SignupRequestDto;
import com.sparta.matchingservice.security.dto.SecurityResponseDto;

//import org.junit.Assert.AssertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;


//@ExtendWith: org.junit.jupiter.api.extension
//MockitoExtension: org.mockito.junit.jupiter


@ExtendWith(MockitoExtension.class) 
public class SecurityServiceTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    private JwtUtil jwtUtil;

    @Spy
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private SecurityService securityService;

    @BeforeEach
    void prepare() {
        ReflectionTestUtils.setField(jwtUtil,
                "secretKey", // jwtUtil의 secretKey값이 저장될 변수
                "7ZWt7ZW0OTntmZTsnbTtjIXtlZzqta3snYTrhIjrqLjshLjqs4TroZzrgpjslYTqsIDsnpDtm4zrpa3tlZzqsJzrsJzsnpDrpbzrp4zrk6TslrTqsIDsnpA="); // secretKey의 값
        jwtUtil.init(); // jwtUtil에서 @PostConstructor가 동작하지 않기 때문에, 임의로 실행시켜야 함
    }

    //@Test: org.junit.jupiter.api
    @Test
    @DisplayName("Sign Up - not Admin")
    void signup() {
        //given
        SignupRequestDto request = SignupRequestDto.builder()
            .userName("Sam")
            .password("1234")
            .nickName("Samsung")
            .profileImage("imgtxt")
            .admin(false)
            .build();

        User user = User.builder().userName("Sam")
                        .password("1234").userRole(UserRole.USER).build();

        when(userRepository.findByUserName(any(String.class)))
                .thenReturn(Optional.empty());

        when(userRepository.save(any(User.class)))
            .thenReturn(user);

        //when
        SecurityResponseDto response = securityService.signup(request);

        //then
        assertThat(response.getStatusCode()).isEqualTo(201);
        assertThat(response.getMsg()).isEqualTo("회원가입 완료");

    }
}
