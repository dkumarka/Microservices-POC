package com.krogerServiceB.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTempConfig {

	@Autowired
	@Lazy
	private RestTemplate restTemplate;

	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
