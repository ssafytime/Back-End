package com.time.ssafy.user.controller;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.HttpResource;
import org.springframework.web.util.CookieGenerator;

import com.time.ssafy.user.jwt.JwtProvider;
import com.time.ssafy.user.model.dto.UserDto;
import com.time.ssafy.user.model.service.UserService;
import com.time.ssafy.user.model.service.UserServiceImpl;


@RestController
@RequestMapping("/users")
public class UserController {
	
	UserServiceImpl userService;
	JwtProvider jwtProvide;
	
	@Autowired
	public UserController(UserServiceImpl userService, JwtProvider jwtProvide) {
		this.userService = userService;
		this.jwtProvide = jwtProvide;
	}

	@PostMapping()
	private ResponseEntity<?> join(@RequestBody UserDto userDto){
		Map<String , String> check = new HashMap<String, String>();
		System.out.println(userDto);
		try {
			check.put("msg", "회원가입에 성공하였습니다.");
			userService.join(userDto);
			
			return new ResponseEntity<Map<String , String>>(check, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();		
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	private ResponseEntity<?> login(@RequestBody Map<String, String> map, HttpServletResponse response){
		Map<String , String> check = new HashMap<String, String>();
		
		try {
			UserDto userDto = userService.login(map);
			if(userDto != null) {
				check.put("msg", "로그인에 성공하였습니다.");
				check.put("niclname", userDto.getNickName());
				
				String key = jwtProvide.createToken(userDto.getUserId(), userDto.getNickName());
				
				Cookie cookie = new Cookie("key", key);
				cookie.setMaxAge(60*60*24); //쿠키 유효 기간: 하루로 설정(60초 * 60분 * 24시간)
			    cookie.setPath("/"); //모든 경로에서 접근 가능하도록 설정
			    response.addCookie(cookie);
				
		        return new ResponseEntity<Map<String , String>>(check, HttpStatus.OK);
			} else {
				check.put("msg", "로그인에 실패하였습니다.");
				return new ResponseEntity<Map<String , String>>(check, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();		
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{userId}")
	private ResponseEntity<?> delete(@PathVariable String userId){
		
		
		
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
