package com.orm2.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebConfig {

	public void addCorsMapping(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:8080")
		.allowedMethods("GET", "POST", "PUT", "PATCH","DELETE");
	}

}
