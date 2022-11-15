package com.time.ssafy.admin.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.time.ssafy.user.jwt.JwtProvider;
import com.time.ssafy.user.model.dto.UserDto;

public class ConfirmInterceptor implements HandlerInterceptor {
	
	JwtProvider jwtProvide;

	@Autowired
	public ConfirmInterceptor(JwtProvider jwtProvide) {
		this.jwtProvide = jwtProvide;
	}


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies();
			
		String key = null;
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("key")) {
				System.out.println(cookie.getValue());
				key = cookie.getValue(); 
			}
		}
		System.out.println(jwtProvide.validateToken(key));
		return jwtProvide.validateToken(key);
	}
	
}
