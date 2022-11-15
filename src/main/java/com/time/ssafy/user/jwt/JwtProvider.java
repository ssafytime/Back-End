package com.time.ssafy.user.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
	private String secretKey = "SsafyTimeSecretKey";
	private static JwtProvider instacne  = new JwtProvider();

	public static JwtProvider getInstance() {
		return instacne;
	}
	
    private long tokenValidTime = 30 * 60 * 1000L;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    

    public String createToken(String userId, String userNickname) {
        Claims claims = Jwts.claims().setSubject(userId); 
        claims.put("nickname", userNickname); 
        
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) 
                .setIssuedAt(now) 
                .setExpiration(new Date(now.getTime() + tokenValidTime)) 
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    
    

    public String getUserInfo(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    

    public boolean validateToken(String jwtToken) {
        try {
        	Claims claims = Jwts.parser()
        			.setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
        			.parseClaimsJws(jwtToken)
        			.getBody();
        	return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }

}
