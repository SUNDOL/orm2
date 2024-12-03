package com.orm2.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.orm2.server.jwt.JwtTokenProvider;

@Configuration
public class JwtConfig {

	@Bean
	public JwtTokenProvider jwtTokenProvier() {
		return new JwtTokenProvider();
	}
	
}
