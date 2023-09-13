package com.krogerServiceB.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krogerServiceB.entity.DeleteEmployeeVO;
import com.krogerServiceB.entity.Employee;
import com.krogerServiceB.entity.EmployeeList;
import com.krogerServiceB.entity.EmployeeVO;
import com.krogerServiceB.exception.EmployeeNotFoundBYIDException;
import com.krogerServiceB.service.EmployeeService;

import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(value = "/service-b/api/v1")
@SecurityRequirement(name = "Bearer Authentication")
public class EmployeeController {
//	 @Value("${custom.someProperty}") // Injecting property from application.yml
//	    private String someProperty;
	@Autowired
	private EmployeeService employeeService;
	
	

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "List<Employee> Object"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@Operation(summary = "Get All Employee")
	@GetMapping(path = "/getEmployees")
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> emps = employeeService.getEmployees();
		if (emps.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(emps));
	}



	
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee Object"),
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@Operation(summary = "Get Employee By ID/IDs")
	@GetMapping(path = "/getEmployeeById/{employeeId}")
	public ResponseEntity<EmployeeList> getEmployeeById(@PathVariable("employeeId") String employeeIds) {
		List<Integer> employeeId = Arrays.stream(employeeIds.split(",")).map(Integer::parseInt)
				.collect(Collectors.toList());
		EmployeeList employeess = employeeService.getEmployeeByIds(employeeId);
		return ResponseEntity.ok(employeess);
	}
	

	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Employee created"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Employee Not Found"),
			@ApiResponse(responseCode = "409", description = "Employee already exists"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@Operation(summary = "Save New Employee")
	@PostMapping(path = "/saveEmployee")
	 public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeVO employeeVO) {
//		  System.out.println("Property from application.yml: " + someProperty);

	        Employee emp = employeeService.saveEmployeeService(employeeVO);
	        return new ResponseEntity<>(emp, HttpStatus.CREATED);
	    }
    

	@ApiResponses(value = { @ApiResponse(responseCode = "201",description = "Employee Updated"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Employee Not Found"),
			@ApiResponse(responseCode = "409", description = "Employee already exists"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@Operation(summary = "Update Existing Employee")
	@PutMapping(path = "/updateEmployee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.updateEmployeeService(employee);
		return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
	}
	
	
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@Operation(summary = "Delete Employee By ID And Status")
	@DeleteMapping(path = "/deleteEmployee")
	public ResponseEntity<String> deleteEmployee(@RequestBody DeleteEmployeeVO employee) {
		if (employee != null) {
			employee = employeeService.deleteEmployee(employee);
			if (employee != null && employee.getStatus().equalsIgnoreCase("Inactive")) {
				return new ResponseEntity<>("Employee Inactivated successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Employee Activated successfully", HttpStatus.OK);
			}
		} else {
			throw new EmployeeNotFoundBYIDException("Employee", "ID", employee.getId());
		}
	}
	
//	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Employee created/updated"),
//	@ApiResponse(responseCode = "400", description = "Bad Request"),
//	@ApiResponse(responseCode = "404", description = "Employe Not Found"),
//	@ApiResponse(responseCode = "409", description = "Employee already exists"),
//	@ApiResponse(responseCode = "500", description = "Internal Server Error") })
//@Operation(summary = "Save Or Update Employee")
//@PostMapping(path = "/saveOrUpdateEmployee")
//public ResponseEntity<Employee> saveOrUpdateEmployee(@RequestBody EmployeeVO employee) {
//Employee emp = employeeService.saveOrUpdateEmployee(employee);
//
//return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
//}
	
	
//	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
//	@ApiResponse(responseCode = "404", description = "Employe Not Found"),
//	@ApiResponse(responseCode = "500", description = "Internal Server Error") })
////@Operation(summary = "Delete Employee By ID And Status")
//@DeleteMapping(path = "/deleteEmployee")
//
//public ResponseEntity<String> deleteEmployee(@RequestBody Employee employee) {
//if (employee != null) {
//	employee = employeeService.deleteEmployee(employee);
//	if (employee != null && employee.getStatus().equalsIgnoreCase("Inactive")) {
//		return new ResponseEntity<>("Employee Inactivated successfully", HttpStatus.OK);
//	} else {
//		return new ResponseEntity<>("Employee Activated successfully", HttpStatus.OK);
//	}
//} else {
//	throw new EmployeeNotFoundBYIDException("Employee", "ID", employee.getId());
//}
//}
	
	//@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
//			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
//			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
//	@Operation(summary = "Delete Employee By ID And Status")
//	@DeleteMapping(path = "/deleteEmployee")
//	public ResponseEntity<String> deleteEmployeeNew(@RequestBody DeleteEmployeeVO employeeVO) {
//
//		if (employeeVO != null) {
//			Employee employee = null;
//			employee = employeeService.deleteEmployee(employee);
//			if (employee != null && employee.getStatus().equalsIgnoreCase("Inactive")) {
//				return new ResponseEntity<>("Employee Inactivated successfully", HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>("Employee Activated successfully", HttpStatus.OK);
//			}
//		} else {
//			throw new EmployeeNotFoundBYIDException("Employee", "ID", employeeVO.getId());
//		}
//	}

}
