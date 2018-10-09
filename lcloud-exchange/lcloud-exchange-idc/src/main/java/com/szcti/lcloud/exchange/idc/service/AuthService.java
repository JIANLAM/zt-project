package com.szcti.lcloud.exchange.idc.service;

import com.szcti.lcloud.exchange.idc.entity.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
