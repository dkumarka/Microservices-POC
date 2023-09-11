
package com.serviceA;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition
@Configuration
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "OAuth", scheme = "bearer")
public class ServiceASwaggerConfig {

	/*
	 * @Bean public OpenAPI baseOpenAPI() { return new OpenAPI().info(new
	 * Info().title("Spring Doc").version("1.0.0").description("spring doc")); }
	 */
}
