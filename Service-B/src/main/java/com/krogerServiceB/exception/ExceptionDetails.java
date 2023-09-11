package com.krogerServiceB.exception;

public class ExceptionDetails {

	private String message;
	private boolean success;

	public ExceptionDetails() {
	}

	public ExceptionDetails(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
