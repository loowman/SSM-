package com.shop.dao;

import org.apache.ibatis.annotations.Param;

import com.shop.entity.User;

public interface UserMapper {
  
     User getUserByNameAndPassWord(@Param("username")String username,@Param("password")String password);
	 User getUserByName(String username);
	 Integer addUser(User user);
}
