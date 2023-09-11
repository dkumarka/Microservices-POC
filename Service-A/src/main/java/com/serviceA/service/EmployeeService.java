package com.serviceA.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serviceA.model.Employee;

@Service
public interface EmployeeService {

	public List<Employee> getAllEmployee(String token);

	public Employee getEmployeeById(Integer employeeId, String token);

	public ResponseEntity<Employee> createEmployee(Integer departmentId, Employee employee, String token);

	public ResponseEntity<Employee> updateEmployee(Employee employee, Integer employeeId, String token);

	public String deleteEmployee(Integer employeeId, String token);

	//public ResponseEntity<Employee> updatePartially(Employee employee, Integer employeeId, String token);
}
