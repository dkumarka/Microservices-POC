package com.krogerServiceB.exception;

import org.springframework.http.HttpStatus;

public class ExceptionDetailsWithHttpsCode {

	private String message;
	private HttpStatus httpStatus;
	
	public ExceptionDetailsWithHttpsCode() {
	}

	public ExceptionDetailsWithHttpsCode(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
