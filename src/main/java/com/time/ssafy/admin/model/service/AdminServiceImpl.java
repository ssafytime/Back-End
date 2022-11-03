package com.time.ssafy.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.time.ssafy.admin.model.dto.Admin;
import com.time.ssafy.admin.model.mapper.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService {

	AdminMapper mapper;
	
	@Autowired
	public AdminServiceImpl(AdminMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Admin> list() throws Exception {
		return mapper.list();
	}
	
	
	
}
