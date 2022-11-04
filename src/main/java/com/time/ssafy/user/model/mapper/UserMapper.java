package com.time.ssafy.user.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.time.ssafy.user.model.dto.UserDto;

@Mapper
public interface UserMapper {
	UserDto login(Map<String, String> map) throws SQLException;
	void join(UserDto userDto) throws SQLException;
}
