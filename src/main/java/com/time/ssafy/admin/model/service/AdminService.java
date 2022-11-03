package com.time.ssafy.admin.model.service;

import java.util.List;

import com.time.ssafy.admin.model.dto.Admin;

public interface AdminService {

	List<Admin> list() throws Exception;
	
}
