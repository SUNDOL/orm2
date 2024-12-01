package com.orm2.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(apiInfo());
	}
	
	private Info apiInfo() {
		return new Info()
				.title("ORM Practice 2")
				.description("ORM for Basic CRUD")
				.version("v1.0.0");
	}
	
}