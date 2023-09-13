package com.serviceA.controller;

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

import com.serviceA.exception.EmployeeNotCreated;
import com.serviceA.exception.EmployeeNotUpdated;
import com.serviceA.model.DeleteEmployeeVO;
import com.serviceA.model.Employee;
import com.serviceA.model.EmployeeList;
import com.serviceA.model.EmployeeVO;
import com.serviceA.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = "/service-a/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved Employee list"),
			@ApiResponse(responseCode = "500", description = " response status is 500"),
			@ApiResponse(responseCode = "403", description = "Accessing the employee you were trying to reach is forbidden"),
			@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
	@Operation(summary = "Get All Employee")
	@GetMapping("/getEmployees")
	@CircuitBreaker(name = "EmployeeService", fallbackMethod = "circuitBreakerService")
	public ResponseEntity<List<Employee>> getAllEmployee(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployee(token), HttpStatus.OK);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Employee not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@Operation(summary = "Get employee by ID/Ids")
	@GetMapping("/getEmployeeById/{employeeIds}")
	public @ResponseBody ResponseEntity<EmployeeList> getEmployee(@PathVariable String employeeIds,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		EmployeeList emps = employeeService.getEmployeeByEmpIds(employeeIds, token);
		return new ResponseEntity<EmployeeList>(emps, HttpStatus.OK);
	}

	@Operation(summary = "Save Employee data")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Employee created"),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "409", description = "Employee already exists"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/saveEmployee")
	public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeVO employee,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		ResponseEntity<Employee> response = employeeService.createEmployee(employee, token);
		if (response.getBody() != null && response.getStatusCode().equals(HttpStatus.CREATED)) {
			return response;
		} else {
			throw new EmployeeNotCreated("Employee Not Created.Please check the Department Id");
		}
	}

	@Operation(summary = "Update employee data")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee successfully Updated"),
			@ApiResponse(responseCode = "400", description = "Invalid EmployeeId supplied"),
			@ApiResponse(responseCode = "404", description = "Employee not found"),
			@ApiResponse(responseCode = "405", description = "Validation exception") })
	@PutMapping("/updateEmployee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		ResponseEntity<Employee> response = employeeService.updateEmployee(employee, token);
		if (response.getBody() != null && response.getStatusCode().equals(HttpStatus.CREATED)) {
			return response;
		} else {
			throw new EmployeeNotUpdated("Employee Not Updated. Please check the Employee Id");
		}
	}

	
	@Operation(summary = "Delete Employee By Id And Status")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid employeeId supplied"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Employee not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@DeleteMapping("/deleteEmployee")
	public ResponseEntity<String> deleteEmployee(@RequestBody DeleteEmployeeVO employee,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		return new ResponseEntity<String>(employeeService.deleteEmployee(employee, token), HttpStatus.OK);
	}
	
	public static ResponseEntity<String> circuitBreakerService(Exception ex) {
		return new ResponseEntity<String>("The service B is down we are unable to fetch the data of the employee",
				HttpStatus.OK);
	}


	
	

//	@Operation(summary = "Save Or Upadate Employee")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee successfully Updated"),
//			@ApiResponse(responseCode = "400", description = "Invalid EmployeeId supplied"),
//			@ApiResponse(responseCode = "404", description = "Employee not found"),
//			@ApiResponse(responseCode = "405", description = "Validation exception") })
//	@PostMapping("/saveOrUpdateEmployee")
//	public ResponseEntity<Employee> saveorUpdateEmployee(@RequestBody EmployeeVO employee,
//			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
//		ResponseEntity<Employee> response = employeeService.saveOrUpdateEmployee(employee, token);
//		if (response.getBody() != null && response.getStatusCode().equals(HttpStatus.CREATED)) {
//			return response;
//		} else {
//			throw new EmployeeNotCreated("Employee Not Created/Updated. Please check the Employee Id");
//		}
//	}

}