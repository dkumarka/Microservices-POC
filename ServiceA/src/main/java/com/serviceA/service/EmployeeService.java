package com.serviceA.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serviceA.model.DeleteEmployeeVO;
import com.serviceA.model.Employee;
import com.serviceA.model.EmployeeList;
import com.serviceA.model.EmployeeVO;


@Service
public interface EmployeeService {

	public List<Employee> getAllEmployee(String token);
	
	public String deleteEmployee(DeleteEmployeeVO employee, String token);
	
	//public ResponseEntity<Employee> saveOrUpdateEmployee(EmployeeVO employee, String token);
	
	public EmployeeList getEmployeeByEmpIds(String employeeIds, String token);
	
	public ResponseEntity<Employee> createEmployee(EmployeeVO employee, String token);
	
	public ResponseEntity<Employee> updateEmployee(Employee employee, String token);
}
