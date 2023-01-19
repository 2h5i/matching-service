package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.user.dto.*;
import com.sparta.matchingservice.user.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SellerService {
    void updateMyProfile(Long userId, UpdateProfileForm updateProfileForm, User user);
    List<SellerProfileResponseDto> allSellerList(int currentPage);

}
