package com.krogerServiceB;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.krogerServiceB.entity.Department;
import com.krogerServiceB.entity.Employee;
import com.krogerServiceB.repo.EmployeeRepository;
import com.krogerServiceB.service.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	private Employee employee;

	@BeforeEach
	void setUp() {
		this.employeeService = new EmployeeServiceImpl(this.employeeRepository);
	}

	@DisplayName("JUnit test for get all employees operation")
	@Test
	public void getEmployeesTest() {
		employeeService.getEmployees();
		verify(employeeRepository).getEmployeesByStatus("Active");
	}

	@DisplayName("JUnit test for get employee by id operation")
	@Test
	public void getEmployeeByIdTest()
	{
		when(employeeRepository.getEmployeeById(1)).thenReturn(new Employee(1, "Dummy2", 23567.00, "active", new Department(01, "manager", "pune", "active")));
		Employee emp = employeeService.getEmployeeById(1);
		assertEquals("Dummy2", emp.getFirstName());
		assertEquals(23567.00, emp.getEmpSalary());
		assertEquals("active", emp.getStatus());
	}

	@DisplayName("JUnit test for save employee operation")
	@Test
	public void saveEmployeeTest() {
		Employee employee = new Employee(02, "Dummy2", 23567.00, "active",
				new Department(01, "manager", "pune", "active"));
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee, employeeService.saveEmployee(employee));
	}

	@DisplayName("JUnit test for delete employee operation")
	@Test
	public void deleteEmployeeTest() {
		Employee employee = new Employee();
		employee.setFirstName("dummy");
		employee.setId(1);
		when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
		employeeService.deleteEmployees(employee.getId());
		verify(employeeRepository).deleteById(employee.getId());
	}

	@DisplayName("JUnit test for update employee operation")
	@Test
	public void updateEmployeeTest() {
		Employee employee = new Employee();
		employee.setFirstName("dummy");
		employee.setEmpSalary(21000.00);

		Employee newemployee = new Employee();
		newemployee.setFirstName("dummy11");
		newemployee.setEmpSalary(11000.00);
		when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
		employeeService.updateEmployee(employee.getId(), newemployee);
		//verify(employeeRepository).save(newemployee);
		verify(employeeRepository).findById(employee.getId());
		
		/*
		 * Employee employeee = new Employee(); int id =101;
		 * employeee.setFirstName("dummy"); employeee.setEmpSalary(21000.00);
		 * employeee.setId(id); employeeRepository.save(employeee);
		 * 
		 * Employee employeeUpdated = employeeRepository.getEmployeeById(id);
		 * assertThat(employeeUpdated.getFirstName()).isEqualTo("Dummy");
		 * assertThat(employeeUpdated.getEmpSalary()).isEqualTo(21000.00);
		 */

	}

}
