package com.krogerServiceB.exception;

public class InternalServerError extends RuntimeException {

	public InternalServerError() {
		super(String.format("Internal Server Erorr"));
	}
}
