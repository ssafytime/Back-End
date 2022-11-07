package com.time.ssafy.user.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.time.ssafy.user.model.dto.UserDto;

public interface UserService {
	UserDto login(Map<String, String> map) throws Exception;
	void join(UserDto userDto) throws SQLException;
	void delete(String userId) throws Exception;
}
