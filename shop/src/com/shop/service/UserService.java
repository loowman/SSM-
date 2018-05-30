package com.shop.service;

import com.shop.entity.User;

public interface UserService {
   User chectLoginUser(String username,String password);
   User getUserByName(String username);
   Integer addUser(User user);
}
