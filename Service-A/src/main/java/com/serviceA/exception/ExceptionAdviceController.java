package com.serviceA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviceController {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> exception(EmployeeNotFoundException exception) {
		ErrorResponse error = new ErrorResponse(exception.getMessage(), exception.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmployeeNotCreated.class)
	public ResponseEntity<ErrorResponse> exception(EmployeeNotCreated exception) {
		ErrorResponse error = new ErrorResponse(exception.getMessage(), exception.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmployeeNotUpdated.class)
	public ResponseEntity<ErrorResponse> exception(EmployeeNotUpdated exception) {
		ErrorResponse error = new ErrorResponse(exception.getMessage(), exception.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<ErrorResponse> exception(InternalServerException exception) {
		ErrorResponse error = new ErrorResponse(exception.getMessage(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/*
	 * @ExceptionHandler(value = ApiNotFoundException.class) public
	 * ResponseEntity<Object> exception(ApiNotFoundException exception,WebRequest
	 * request) { ExceptionDetails error = new
	 * ExceptionDetails(exception.getMessage(), request.getDescription(false));
	 * return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
	 * 
	 * }
	 * 
	 * //Global exceptions
	 * 
	 * @ExceptionHandler(value = Exception.class) public ResponseEntity<Object>
	 * exception(Exception exception,WebRequest request) { ExceptionDetails error =
	 * new ExceptionDetails(exception.getMessage(), request.getDescription(false));
	 * return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
	 * 
	 * }
	 */
}
