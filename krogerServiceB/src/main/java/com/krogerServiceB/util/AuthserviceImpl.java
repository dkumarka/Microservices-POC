package com.krogerServiceB.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AuthserviceImpl implements Authservice {

	private final RestTemplate restTemplate = new RestTemplate();
	
	@Value("${dynamic.id.address.service.oauth}")
    String ip_address_get_oauth;

	@Override
	public String validateAuth(String token) {
		String token1 = ip_address_get_oauth + "/oauth/check_token?token=" + token;
		Root authValidation = restTemplate.getForObject(token1, Root.class);
		if (authValidation != null) {
			return "User Validate";
		} else {
			return "User Not Validate";
		}
	}

}
