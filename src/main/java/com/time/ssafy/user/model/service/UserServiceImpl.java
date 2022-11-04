package com.time.ssafy.user.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.time.ssafy.user.model.dto.UserDto;
import com.time.ssafy.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserDto login(Map<String, String> map) throws Exception {
		return userMapper.login(map);
	}
	
	
	
}
