package com.sparta.matchingservice.security.service;

import com.sparta.matchingservice.security.dto.LoginRequestDto;
import com.sparta.matchingservice.security.dto.SignupRequestDto;
import com.sparta.matchingservice.security.dto.SecurityResponseDto;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.security.util.JwtUtil;
import com.sparta.matchingservice.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.redis.core.RedisTemplate;

import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private final RedisTemplate redisTemplate;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";


    @Transactional
    public SecurityResponseDto signup(SignupRequestDto signupRequestDto) {
        String userName = signupRequestDto.getUserName();
        String nickName = signupRequestDto.getNickName();
        String profileImage = signupRequestDto.getProfileImage();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUserName(userName);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 userName 입니다");
        }

        UserRole role = UserRole.USER;

        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRole.ADMIN;
        }
        Profile profile = Profile.createWithoutIntroduce()
                            .nickName(nickName)
                            .profileImage(profileImage)
                            .build();

        User user = User.builder()
                    .userName(userName)
                    .password(password)
                    .userRole(role)
                    .profile(profile)
                    .build();
        User savedUser = userRepository.save(user);
        return new SecurityResponseDto("회원가입 완료",201,(savedUser.getUserRole().getAuthority() == "ROLE_ADMIN"));
    }

    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String userName = loginRequestDto.getUserName();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new IllegalArgumentException("회원 아이디가 없습니다")
        );
        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw  new IllegalArgumentException("비밀번호가 틀렸습니다");
        }
        String generatedToken = jwtUtil.createToken(user.getUserName(), user.getUserRole());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, generatedToken);
    }
    public void logout(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Long expiration = jwtUtil.getExpiration(token);
        redisTemplate.opsForValue().set(token, "logout", expiration, TimeUnit.MILLISECONDS);
    }
}
