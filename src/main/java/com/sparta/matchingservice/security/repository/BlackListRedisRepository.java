package com.sparta.matchingservice.security.repository;

import com.sparta.matchingservice.security.entity.BlackList;

import org.springframework.data.repository.CrudRepository;

public interface BlackListRedisRepository extends CrudRepository<BlackList, Long>{
}
