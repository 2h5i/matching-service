package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.user.dto.ModifyUserProfileRequestDto;
import com.sparta.matchingservice.user.dto.UserProfileResponseDto;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.repository.UserRepository;
import com.sparta.matchingservice.user.exception.IdNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final UserRepository userRepository;

    //프로필 수정 서비스

    @Override
    @Transactional
    public UserProfileResponseDto modifyUserProfile(ModifyUserProfileRequestDto modifyUserProfileRequestDto, Long id) {
        String profileImage = modifyUserProfileRequestDto.getProfileImage();
        String nickName = modifyUserProfileRequestDto.getNickName();

        //레파지토리에서 user 객체 찾기

        User user = userRepository.findById(id).orElseThrow(IdNotFoundException::new);

        // 프로필 내용수정
       user.builder()
                .profile(
                        Profile.builder()
                                .profileImage(profileImage)
                                .nickName(nickName)
                                .build())
                .build();
        // 유저에 프로필 닉네임이 잘 수정됐나 테스트...
        System.out.println(user.getProfile().getNickName().toString());

        return new UserProfileResponseDto(user);
    }
}
