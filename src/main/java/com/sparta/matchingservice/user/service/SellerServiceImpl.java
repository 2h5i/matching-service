package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.item.repository.ItemRepository;
import com.sparta.matchingservice.user.dto.*;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
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

}
