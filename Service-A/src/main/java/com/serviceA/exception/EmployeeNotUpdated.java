package com.serviceA.exception;

public class EmployeeNotUpdated extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeNotUpdated(String message) {

		super(message);

	}
}
