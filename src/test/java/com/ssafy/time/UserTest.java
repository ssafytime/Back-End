package com.ssafy.time;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.time.ssafy.user.jwt.JwtProvider;
import com.time.ssafy.user.model.dto.UserDto;
import com.time.ssafy.user.model.service.UserServiceImpl;


public class UserTest extends UnitTestConfig {
	String key = null;
	private final Logger logger = LoggerFactory.getLogger(UserTest.class);
	
	JwtProvider jp = JwtProvider.getInstance();
	
	@Autowired
	UserServiceImpl usi;

	@BeforeClass
	public static void beforeClass() {
		System.out.println("----- Class Test Start!!! -----");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("----- Class Test End!!! -----");
	}
	
	@Before
	public void beforeMethod() {
		System.out.println("----- Method Test Start!!! -----");
	}
	
	@After
	public void afterMethod() {
		System.out.println("----- Method Test Snd!!! -----");
	}
	
	@Test
	@Before
	// 단위테스트
	public void 토큰_생성() {
		key = jp.createToken("ssafy", "1234");
		assertNotNull(key);
	}
	
	@Test
	public void 토큰_확인() {
		assertTrue(jp.validateToken(key));
	}
	
	@Test
	public void 토큰_정보_추출() {
		String userId = jp.getUserInfo(key);
		assertEquals(userId, "ssafy");
	}
	
	@Test
	// 통합테스트
	public void 로그인() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", "ssafy");
		map.put("userPwd", "1234");		
		
		UserDto userDto = null;
		
		try {
			userDto = usi.login(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		assertNotNull(userDto);
	}
	
}