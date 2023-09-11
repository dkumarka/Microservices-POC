package com.krogerServiceB.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krogerServiceB.entity.Department;
import com.krogerServiceB.entity.Employee;

@Service
public interface EmployeeService {
	List<Employee> getEmployees();

	Employee getEmployeeById(int id);

	Employee saveEmployee(Department department, Employee employee);

	Employee deleteEmployee(int id);

	Employee updateEmployee(Employee employee, int id);

	// Employee partialUpdateEmployee(int id, Map<String, Object> fields);
}
