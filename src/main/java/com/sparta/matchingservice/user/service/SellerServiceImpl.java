package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.item.repository.ItemRepository;
import com.sparta.matchingservice.user.dto.*;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SellerServiceImpl implements SellerService{
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public void updateMyProfile(Long userId, UpdateProfileForm updateProfileForm, User user) {
        if (user.getId() == userId) {
            // 시큐리티에서 존재하는 유저인지 체크해서 줄 것
            User findUser = userRepository.findById(userId).get();
            Profile findUserProfile = findUser.getProfile();
            findUserProfile.updateSellerProfile(updateProfileForm.getNickName(),
                    updateProfileForm.getProfileImage(), updateProfileForm.getIntroduce());
            userRepository.save(findUser);
        }
    }


    //전체셀러 목록 조회
    @Override
    public List<SellerProfileResponseDto> allSellerList(int currentPage){
        if(currentPage==0) currentPage=1;
        Page<User> userAll = userRepository.findAll(PageRequest.of(currentPage-1,10, Sort.Direction.DESC));

        List<SellerProfileResponseDto> sellerProfileResponseDtos = new ArrayList<>();

        for(User user:userAll){
            if(!user.getProfile().getIntroduce().isEmpty()) {
                sellerProfileResponseDtos.add(new SellerProfileResponseDto(user.getUserName(), user.getProfile().getIntroduce()));
            }
        }

        return sellerProfileResponseDtos;


    }

}
