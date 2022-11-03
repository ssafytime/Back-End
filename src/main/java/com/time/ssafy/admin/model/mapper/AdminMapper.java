package com.time.ssafy.admin.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.time.ssafy.admin.model.dto.Admin;

@Mapper
public interface AdminMapper {
	
	List<Admin> list() throws SQLException;
	
}
