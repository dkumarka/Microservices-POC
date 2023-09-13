package com.serviceA.exception;

public class AuthTokenInvalidException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AuthTokenInvalidException(String token) {
		super("Auth Token is not valide=" + token);
	}
}
