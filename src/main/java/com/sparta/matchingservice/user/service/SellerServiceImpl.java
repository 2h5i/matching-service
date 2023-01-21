package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.common.exception.IdNotFoundException;
import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.item.repository.ItemRepository;
import com.sparta.matchingservice.user.dto.*;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
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
    @Transactional(readOnly = true)
    public List<SellerProfileResponseDto> allSellerList(int currentPage){

        if(currentPage==0) currentPage=1;
        Page<User> usersByUserRole = userRepository.findUsersByUserRole(UserRole.SELLER, PageRequest.of(currentPage-1, 3, Sort.by("id").descending()));

        List<SellerProfileResponseDto> sellerProfileResponseDtos = new ArrayList<>();

        for(User user:usersByUserRole){
            if(!user.getProfile().getIntroduce().isEmpty()) {
                sellerProfileResponseDtos.add(new SellerProfileResponseDto(user.getUserName(), user.getProfile().getIntroduce()));
            }
        }

        return sellerProfileResponseDtos;

    }

    @Override
    @Transactional(readOnly = true)
    public SelectedSellerResponseDto selectSeller(Long userid, int currentPage){
        //레파지토리에서  List<Item> sellerItem 를 뽑아내야함.
        //user 객체를 통째로 넘겨받아서
        //아이템 레파지토리에 접근을 해서 그 아이템 레파지토리에서 파인드바이 유저로
        if(currentPage==0) currentPage=1;
        User user =userRepository.findById(userid).orElseThrow(IdNotFoundException::new);
        //리스트로 어떻게 가져오지..?
        Page<Item> byUserItemPage = itemRepository.findByUserId(user.getId(),PageRequest.of(currentPage-1, 3, Sort.by("id").descending()));
        List<Item> itemList = new ArrayList<>();

        for (Item item:byUserItemPage) {
            if(item.getIsAvailable())itemList.add(item);
        }


        return new SelectedSellerResponseDto(user.getUserName(), user.getProfile().getIntroduce(),itemList);

    }



}
