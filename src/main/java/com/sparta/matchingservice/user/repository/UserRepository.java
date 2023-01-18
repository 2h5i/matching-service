package com.sparta.matchingservice.user.repository;

import com.sparta.matchingservice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository{
}
