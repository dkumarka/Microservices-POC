package com.krogerServiceB.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.krogerServiceB.entity.Department;
import com.krogerServiceB.entity.Employee;
import com.krogerServiceB.exception.EmployeeNotFoundBYIDException;
import com.krogerServiceB.service.DepartmentService;
import com.krogerServiceB.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(value = "/api/v1")
@SecurityRequirement(name = "Bearer Authentication")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

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
	@Operation(summary = "Get Employee By ID")
	@GetMapping(path = "/getEmployee/{employeeId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("employeeId") int id) {
		Employee emp = employeeService.getEmployeeById(id);
		if (emp == null) {
			throw new EmployeeNotFoundBYIDException("Employee", "ID", id);
		}
		return ResponseEntity.of(Optional.of(emp));
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Employee created"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "409", description = "Employee already exists"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@Operation(summary = "Save New Employee")
	@PostMapping(path = "/saveEmployee")
	public ResponseEntity<Employee> saveEmployee(@RequestParam(name = "departmentId") int id,
			@RequestBody Employee employee) {
		Employee emp = null;
		Department department = departmentService.getDepartmentById(id);
		if (department != null) {
			emp = employeeService.saveEmployee(department, employee);
			return ResponseEntity.of(Optional.of(emp));
		} else {
			throw new EmployeeNotFoundBYIDException("Department", "ID", id);
		}
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@Operation(summary = "Delete Employee By ID")
	@DeleteMapping(path = "/deleteEmployee/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") int id) {
		Employee employee = null;
		employee = employeeService.deleteEmployee(id);
		if (employee != null && employee.getStatus() == "Inactive") {
			return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
		} else {
			throw new EmployeeNotFoundBYIDException("Employee", "ID", id);
		}
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@Operation(summary = "Update Employee By ID")
	@PutMapping(path = "/updateEmployee/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("employeeId") int id) {
		Employee emp = null;
		emp = employeeService.updateEmployee(employee, id);
		if (emp != null) {
			return ResponseEntity.ok().body(emp);
		} else {
			throw new EmployeeNotFoundBYIDException("Employee", "ID", id);
		}
	}
	
	/*
	 * @ApiResponses(value = { @ApiResponse(responseCode = "400", description =
	 * "Invalid ID supplied"),
	 * 
	 * @ApiResponse(responseCode = "404", description = "Employe Not Found"),
	 * 
	 * @ApiResponse(responseCode = "500", description = "Internal Server Error") })
	 * 
	 * @Operation(summary = "Partial Update Employee By ID")
	 * 
	 * @PatchMapping(path = "/patialUpdateEmployee/{employeeId}") public
	 * ResponseEntity<Employee> partialUpdateEmployee(@PathVariable("employeeId")
	 * int id, @RequestBody Map<String, Object> fields) { Employee emp = null; emp =
	 * employeeService.partialUpdateEmployee(id, fields); if (emp != null) { return
	 * ResponseEntity.ok().body(emp); } else { throw new
	 * EmployeeNotFoundBYIDException("Employee", "ID", id); } }
	 */

}
