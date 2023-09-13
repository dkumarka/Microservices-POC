package com.krogerServiceB.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krogerServiceB.entity.DeleteEmployeeVO;
import com.krogerServiceB.entity.Employee;
import com.krogerServiceB.entity.EmployeeList;
import com.krogerServiceB.entity.EmployeeVO;

@Service
public interface EmployeeService {
	List<Employee> getEmployees();

	Employee getEmployeeById(int id);

//	Employee saveOrUpdateEmployee(EmployeeVO employee);

	DeleteEmployeeVO deleteEmployee(DeleteEmployeeVO employee);

	EmployeeList getEmployeeByIds(List<Integer> employeeId);

	Employee saveEmployeeService(EmployeeVO employeeVO);

	Employee updateEmployeeService(Employee employee);
}
