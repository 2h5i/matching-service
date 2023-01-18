package com.sparta.matchingservice.user.repository;

import com.sparta.matchingservice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying(clearAutomatically = true)
    Optional<User> findById(Long id);
    Optional<User> findByUserName(String userName);

}
