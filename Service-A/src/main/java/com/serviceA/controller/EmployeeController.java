package com.serviceA.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.serviceA.exception.EmployeeNotCreated;
import com.serviceA.exception.EmployeeNotUpdated;
import com.serviceA.exception.InternalServerException;
import com.serviceA.model.Employee;
import com.serviceA.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = "/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved Employee list"),
			@ApiResponse(responseCode = "500", description = " response status is 500"),
			@ApiResponse(responseCode = "403", description = "Accessing the employee you were trying to reach is forbidden"),
			@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
	@Operation(summary = "Get All Employee")
	@GetMapping("/getAllEmployee")
	@CircuitBreaker(name = "EmployeeService", fallbackMethod = "circuitBreakerService")
	public ResponseEntity<List<Employee>> getAllEmployee(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployee(token), HttpStatus.OK);
	}
	
	
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Employee not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@Operation(summary = "Get employee by ID")
	@GetMapping("/getEmployee/{employeeId}")
	//@CircuitBreaker(name = "EmployeeService", fallbackMethod = "circuitBreakerService")
	public @ResponseBody ResponseEntity<Employee> getEmployee(@PathVariable Integer employeeId,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		Employee emp = employeeService.getEmployeeById(employeeId, token);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

	@Operation(summary = "Save Employee data")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Employee created"),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "409", description = "Employee already exists"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/saveEmployee/{departmentId}")
//	@CircuitBreaker(name = "EmployeeService", fallbackMethod = "circuitBreakerService")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, @PathVariable Integer departmentId,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		ResponseEntity<Employee> response = employeeService.createEmployee(departmentId, employee, token);
		if (response.getBody() != null && response.getStatusCode().equals(HttpStatus.OK)) {
			return response;
		} else {
			throw new EmployeeNotCreated("Employee Not Created");
		}
	}

	@Operation(summary = "Update employee data")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee successfully Updated"),
			@ApiResponse(responseCode = "400", description = "Invalid EmployeeId supplied"),
			@ApiResponse(responseCode = "404", description = "Employee not found"),
			@ApiResponse(responseCode = "405", description = "Validation exception") })
	@PutMapping("/updateEmployee/{employeeId}")
	// @CircuitBreaker(name = "EmployeeService", fallbackMethod =
	// "circuitBreakerService")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Integer employeeId,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		ResponseEntity<Employee> response = employeeService.updateEmployee(employee, employeeId, token);
		if (response.getBody() != null && response.getStatusCode().equals(HttpStatus.OK)) {
			return response;
		} else {
			throw new EmployeeNotUpdated("Employee Not Updated. Please check the Employee Id");
		}
	}

	@Operation(summary = "Delete Employee")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid employeeId supplied"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Employee not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@DeleteMapping("/deleteEmployee/{employeeId}")
	// @CircuitBreaker(name = "EmployeeService", fallbackMethod =
	// "circuitBreakerService")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer employeeId,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		return new ResponseEntity<String>(employeeService.deleteEmployee(employeeId, token), HttpStatus.OK);
	}
	
	public static ResponseEntity<String> circuitBreakerService(Exception ex) {
		return new ResponseEntity<String>("The service B is down we are unable to fetch the data of the employee",
				HttpStatus.OK);
	}

	/*
	 * @Operation(summary = "update partially Employee")
	 * 
	 * @ApiResponses(value = { @ApiResponse(responseCode = "200", description =
	 * "Employee data successfully Updated"),
	 * 
	 * @ApiResponse(responseCode = "400", description =
	 * "Invalid EmployeeId supplied"),
	 * 
	 * @ApiResponse(responseCode = "404", description = "Employee not found"),
	 * 
	 * @ApiResponse(responseCode = "405", description = "Validation exception") })
	 * 
	 * @PatchMapping("/partialUpdate/{employeeId}")
	 * 
	 * @CircuitBreaker(name = "EmployeeService", fallbackMethod =
	 * "circuitBreakerService") public ResponseEntity<Employee>
	 * updatePartially(@RequestBody Employee employee, @PathVariable Integer
	 * employeeId,
	 * 
	 * @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
	 * logger.info("inside updatePartially Method"); ResponseEntity<Employee> emp =
	 * null; try { emp = employeeService.updatePartially(employee, employeeId,
	 * token); } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } return
	 * emp; }
	 */

}