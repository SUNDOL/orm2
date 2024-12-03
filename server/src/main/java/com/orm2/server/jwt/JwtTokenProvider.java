package com.orm2.server.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;

	public String generateToken(String email) {
		long expirationTime = 86400000L; // 1일

		Key signingKey = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());
		return Jwts.builder().setSubject(email).setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(signingKey).compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token); // 토큰이 유효하면 예외가 발생하지
																									// 않음
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getEmailFromToken(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
}
