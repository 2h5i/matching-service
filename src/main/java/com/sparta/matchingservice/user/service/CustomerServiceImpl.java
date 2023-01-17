package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.user.dto.ModifyUserProfileRequestDto;
import com.sparta.matchingservice.user.dto.UserProfileResponseDto;
import com.sparta.matchingservice.user.dto.UsersignupRequestDto;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.SellerEnrollment;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.user.repository.UserRepository;
import com.sparta.matchingservice.user.exception.IdNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final UserRepository userRepository;

    //프로필 수정 서비스

    @Override
    @Transactional
    public UserProfileResponseDto modifyUserProfile(ModifyUserProfileRequestDto modifyUserProfileRequestDto, Long id) {
        String profileImage = modifyUserProfileRequestDto.getProfileImage().toString();
        String nickName = modifyUserProfileRequestDto.getNickName().toString();

        //레파지토리에서 user 객체 찾기

        User user = userRepository.findById(id).orElseThrow(IdNotFoundException::new);

        // 프로필 내용수정

        user.modufyProfile(nickName,profileImage);

        // 유저에 프로필 닉네임이 잘 수정됐나 테스트...
        System.out.println("레파지토리 저장 전 유저닉네임 : " + user.getProfile().getNickName().toString());
        userRepository.save(user);
        System.out.println("레파지토리에 저장 후 유저닉네임 : " + userRepository.findById(id).get().getProfile().getNickName().toString());

        return new UserProfileResponseDto(user);

    }

//    @Override
//    @Transactional
//    public void signup() {
////        User user = User.builder()
////                .password(usersignupRequestDto.getPassword())
////                .userName(usersignupRequestDto.getUserName())
////                .userRole(usersignupRequestDto.getUserRole())
////                .profile(usersignupRequestDto.getProfile())
////                .build();
//
//        User user = new User(1L,"banana","hahaha",new Profile("banana","sdfasdf"), UserRole.USER, SellerEnrollment.NONE);
//        userRepository.save(user);
//    }
}
