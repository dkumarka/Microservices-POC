package com.serviceA.exception;

public class EmployeeNotCreated extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmployeeNotCreated(String message) {

		super(message);
	}
}
