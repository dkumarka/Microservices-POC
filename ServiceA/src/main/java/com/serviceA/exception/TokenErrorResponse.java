package com.serviceA.exception;

import org.springframework.http.HttpStatus;

public class TokenErrorResponse {


	private String message;
	private String details;
	private HttpStatus statusCode;

	public TokenErrorResponse(String message, String details, HttpStatus statusCode) {
		super();
		this.message = message;
		this.details = details;
		this.statusCode = statusCode;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}

