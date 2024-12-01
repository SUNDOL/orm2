package com.orm2.server.config;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.orm2.server.jwt.JwtAuthenticationFilter;
import com.orm2.server.jwt.JwtAuthorizationFilter;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private String secretKey;

	@PostConstruct
	public void init() {
		this.secretKey = generateSecretKey();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager)
			throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/login", "/logout", "/register").permitAll()
				.requestMatchers("/member/**").authenticated().requestMatchers(HttpMethod.GET, "/board", "/board/{id}")
				.permitAll().requestMatchers(HttpMethod.POST, "/board/{id}").authenticated()
				.requestMatchers(HttpMethod.PUT, "/board/{id}").authenticated()
				.requestMatchers(HttpMethod.DELETE, "/board/{id}").authenticated().anyRequest().denyAll())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilter(new JwtAuthenticationFilter(authenticationManager, secretKey))
				.addFilterBefore(new JwtAuthorizationFilter(secretKey), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	private String generateSecretKey() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] randomBytes = new byte[32];
		secureRandom.nextBytes(randomBytes);
		return Base64.getEncoder().encodeToString(randomBytes);
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}