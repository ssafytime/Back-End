package com.time.ssafy.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.time.ssafy.admin.model.dto.Admin;
import com.time.ssafy.admin.model.service.AdminService;
import com.time.ssafy.admin.model.service.AdminServiceImpl;


@RestController
public class AdminController {
	
	AdminServiceImpl service;
	
	@Autowired
	public AdminController(AdminServiceImpl service){
		this.service = service;
	}
	
	@GetMapping("/admin")
	public ResponseEntity<?> list(){
		
		try {
			List<Admin> list = service.list();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Admin>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}

	} 
}
