package com.shop.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.UserMapper;
import com.shop.entity.User;
import com.shop.service.UserService;
@Service("userService")
public class UserServiceImp implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User chectLoginUser(String username, String password) {
		// TODO Auto-generated method stub
		User user=null;
		user=userMapper.getUserByNameAndPassWord(username, password);
		
		return user;
	}

	@Override
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return userMapper.getUserByName(username);
	}

	@Override
	public Integer addUser(User user) {
		// TODO Auto-generated method stub
		
		return userMapper.addUser(user);
	}

}
