package com.krogerServiceB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.krogerServiceB.entity.Department;
import com.krogerServiceB.entity.Employee;
import com.krogerServiceB.exception.EmployeeNotFoundException;
import com.krogerServiceB.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployees() {
		List<Employee> emps = employeeRepository.getEmployeesByStatus("Active");
		return emps;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = employeeRepository.getEmployeeById(id);
		return employee;
	}

	@Override
	public Employee saveEmployee(Department department, Employee employee) {
		Employee emp = null;
		if (employee != null) {
			emp = new Employee();
			emp.setFirstName(employee.getFirstName());
			emp.setEmpSalary(employee.getEmpSalary());
			emp.setStatus("Active");
			emp.setDepartment(department);
			emp = employeeRepository.save(emp);
		}
		return emp;
	}

	@Override
	public Employee deleteEmployee(int id) {
		Employee employee = employeeRepository.getEmployeeById(id);
		if (employee != null) {
			employee.setStatus("Inactive");
			employee = employeeRepository.save(employee);
		}
		return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee, int id) {
		Employee employee2 = getEmployeeById(id);
		if (employee2 != null) {
			if (employee.getFirstName() != null) {
				employee2.setFirstName(employee.getFirstName());
			}
			if (employee.getEmpSalary() != null) {
				employee2.setEmpSalary(employee.getEmpSalary());
			}
			if (employee.getStatus() != null && employee.getStatus().equalsIgnoreCase("Inactive")) {
				employee2.setStatus("Inactive");
			} else {
				employee2.setStatus("Active");
			}
			employee2 = employeeRepository.save(employee2);
		}
		return employee2;
	}

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public void deleteEmployees(int id) {
		employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		employeeRepository.deleteById(id);
	}

	public Employee updateEmployee(int id, Employee employee) {
		Employee employee2 = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		if (employee.getFirstName() != null) {
			employee2.setFirstName(employee.getFirstName());
		}
		if (employee.getEmpSalary() != null) {
			employee2.setEmpSalary(employee.getEmpSalary());
		}
		if (employee.getStatus() != null) {
			employee2.setStatus(employee.getStatus());
		}
		// employeeRepository.findById(id).orElseThrow(() -> new
		// EmployeeNotFoundException(id));
		return employeeRepository.save(employee2);
	}
	
	/*
	 * @Override public Employee partialUpdateEmployee(int id, Map<String, Object>
	 * fields) { Employee existingEmp= employeeRepository.getEmployeeById(id); if
	 * (existingEmp != null) { fields.forEach((key,value) ->{ Field field=
	 * ReflectionUtils.findField(Employee.class, key); field.setAccessible(true);
	 * ReflectionUtils.setField(field, existingEmp, value); }); return
	 * employeeRepository.save(existingEmp); } throw new
	 * EmployeeNotFoundBYIDException("Employee", "ID", id); }
	 */

}
