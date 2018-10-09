package com.szcti.lcloud.exchange.idc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.szcti.lcloud.exchange.idc.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByAppid(String username);
}
