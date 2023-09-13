package com.serviceA.util;

import org.springframework.stereotype.Service;

@Service
public interface Authservice {

	String validateAuth(String token);
}
