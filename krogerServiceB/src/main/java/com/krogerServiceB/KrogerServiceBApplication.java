package com.krogerServiceB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "Service B APIs", version = "1.0", description = "APIs Information"))
//@SecurityScheme(name = "serviceB", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class KrogerServiceBApplication {

	public static void main(String[] args) {
		SpringApplication.run(KrogerServiceBApplication.class, args);
	}

}
