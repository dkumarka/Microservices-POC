package com.krogerServiceB.exception;

public class AuthTokenInvalidException extends RuntimeException {
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

	String fieldValue;

	public AuthTokenInvalidException(String fieldValue) {
		super(String.format("Authorization Token: %s Is Not Valid ", fieldValue));
		this.fieldValue = fieldValue;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

}
