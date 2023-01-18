package com.sparta.matchingservice.security.service;

import com.sparta.matchingservice.security.dto.LoginRequestDto;
import com.sparta.matchingservice.security.dto.SignupRequestDto;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.security.util.JwtUtil;
import com.sparta.matchingservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";


    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        String userName = signupRequestDto.getUserName();
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

        User user = User.builder()
                    .userName(userName)
                    .password(password)
                    .userRole(role)
                    .build();
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public String login(LoginRequestDto loginRequestDto) {
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
        return jwtUtil.createToken(user.getUserName(), user.getUserRole());
    }
}
