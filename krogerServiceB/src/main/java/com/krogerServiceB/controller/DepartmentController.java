package com.krogerServiceB.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.krogerServiceB.entity.Department;
import com.krogerServiceB.exception.EmployeeNotFoundBYIDException;
import com.krogerServiceB.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(value = "/service-b/api/v1")
@SecurityRequirement(name = "Bearer Authentication")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Employee created"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "409", description = "Employee already exists"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@Operation(summary = "Save New Department")
	@PostMapping(path = "/saveDepartment")
	public ResponseEntity<Department> saveEmployee(@RequestBody Department department) {
		Department dept = null;
		try {
			dept = departmentService.saveDepartment(department);
			return ResponseEntity.of(Optional.of(dept));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "List<Employee> Object"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@Operation(summary = "Get All Department")
	@GetMapping(path = "/getDepartments")
	public ResponseEntity<List<Department>> getDepartments() {
		List<Department> dept = departmentService.getDepartments();
		if (dept.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(dept));
	}
	
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee Object"),
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
			@ApiResponse(responseCode = "404", description = "Employe Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@Operation(summary = "Get Department By ID")
	@GetMapping(path = "/getDepartment/{departmentId}")
	public ResponseEntity<Department> getDepartment(@PathVariable("departmentId") int id) {
		Department dept = departmentService.getDepartmentById(id);
		if (dept == null) {
			throw new EmployeeNotFoundBYIDException("Employee", "ID", id);
		}
		return ResponseEntity.of(Optional.of(dept));
	}
}
