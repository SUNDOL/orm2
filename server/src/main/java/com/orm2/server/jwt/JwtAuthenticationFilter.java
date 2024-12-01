package com.orm2.server.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	
	private String secretKey;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, String secretKey) {
		this.authenticationManager = authenticationManager;
		this.secretKey = secretKey;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
				password);
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) {
		String token = generateJwtToken(authResult);
		response.addHeader("Authorization", "Bearer " + token);
	}

	private String generateJwtToken(Authentication authResult) {
		String email = authResult.getName();
		long expirationTime = 86400000L;
		Key signingKey = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());
		return Jwts.builder().setSubject(email).setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(signingKey).compact();
	}
	
}