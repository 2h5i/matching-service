package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.item.repository.ItemRepository;
import com.sparta.matchingservice.user.dto.ItemsResponseDto;
import com.sparta.matchingservice.user.dto.ModifyUserProfileRequestDto;
import com.sparta.matchingservice.user.dto.UserProfileResponseDto;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.common.exception.UserNameNotFoundException;
import com.sparta.matchingservice.user.repository.UserRepository;
import com.sparta.matchingservice.common.exception.IdNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        userRepository.save(user);
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
