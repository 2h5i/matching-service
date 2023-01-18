package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.user.dto.ModifyUserProfileRequestDto;
import com.sparta.matchingservice.user.dto.UserProfileResponseDto;
import com.sparta.matchingservice.user.dto.UsersignupRequestDto;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.SellerEnrollment;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.user.exception.UserNameNotFoundException;
import com.sparta.matchingservice.user.repository.UserRepository;
import com.sparta.matchingservice.user.exception.IdNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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
        System.out.println("레파지토리 저장 전 유저닉네임 : " + user.getProfile().getNickName().toString()); //Todo 나중에 지우기
        userRepository.save(user);
        System.out.println("레파지토리에 저장 후 유저닉네임 : " + userRepository.findById(id).get().getProfile().getNickName().toString()); // Todo 나중에 지우기2

        return new UserProfileResponseDto(user);

    }

    //내 프로필 조회
    @Override
    @Transactional(readOnly = true)
    public UserProfileResponseDto readProfile(String userName){
        //userName으로 레파지토리에서 프로필 찾기
        User user = userRepository.findByUserName(userName).orElseThrow(UserNameNotFoundException::new);
        return new UserProfileResponseDto(user);
    }




}
