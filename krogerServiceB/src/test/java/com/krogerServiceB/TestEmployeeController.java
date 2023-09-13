package com.krogerServiceB;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.krogerServiceB.entity.Department;
import com.krogerServiceB.entity.Employee;
import com.krogerServiceB.entity.EmployeeList;
import com.krogerServiceB.entity.EmployeeVO;
import com.krogerServiceB.repo.EmployeeRepository;
import com.krogerServiceB.service.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeController {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@BeforeEach
	void setUp() {
		this.employeeService = new EmployeeServiceImpl(this.employeeRepository);
	}

	@DisplayName("Test Case for get All Active Employees")
	@Test
	public void testGetEmployees() {
		// Create sample employees
		Employee employee1 = new Employee(1, "Test1", 23567.00, "Active", new Department(01, "IT", "Pune", "Active"));
		Employee employee2 = new Employee(2, "Test2", 23568.00, "Active", new Department(01, "HR", "Pune", "Active"));

		// Mock the behavior of the repository to return the sample employees when
		// getEmployeesByStatus("Active") is called
		when(employeeRepository.getEmployeesByStatus("Active")).thenReturn(Arrays.asList(employee1, employee2));

		// Call the service method
		List<Employee> employees = employeeService.getEmployees();

		// Verify that the repository method was called
		verify(employeeRepository, times(1)).getEmployeesByStatus("Active");

		// Assertions
		assertEquals(2, employees.size());
		assertEquals("Test1", employees.get(0).getFirstName());
		assertEquals("Test2", employees.get(1).getFirstName());
	}

	/*
	 * @DisplayName("Test Case For Delete Employee By Status")
	 * 
	 * @Test public void testDeleteEmployee() { // Arrange int employeeId = 1;
	 * Employee employee = new Employee(); employee.setId(employeeId);
	 * employee.setStatus("Inactive");
	 * 
	 * Mockito.when(employeeRepository.getEmployeeByIdWithoutStatus(employeeId)).
	 * thenReturn(employee);
	 * Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn
	 * (employee);
	 * 
	 * // Act employeeService.deleteEmployee(employee);
	 * 
	 * // Assert assertEquals("Inactive", employee.getStatus());
	 * Mockito.verify(employeeRepository, Mockito.times(1)).save(employee); }
	 */

	@Test
	public void testGetEmployeesByIds() {
		// Arrange
		List<Integer> employeeIds = Arrays.asList(1, 2, 3);
		List<Employee> expectedEmployees = Arrays.asList(new Employee(1, "Test1", 23567.00, "Active", null),
				new Employee(2, "Test2", 23567.00, "Active", null), new Employee(3, "Test3", 23567.00, "Active", null));

		Mockito.when(employeeRepository.getEmployeeByIds(employeeIds)).thenReturn(expectedEmployees);

		// Act
		EmployeeList actualEmployees = employeeService.getEmployeeByIds(employeeIds);

		// Assert
		assertEquals(expectedEmployees, actualEmployees.getEmpList());
	}

	/*
	 * @Test public void testSaveOrUpdateEmployee() { // Arrange Employee
	 * employeeToSaveOrUpdate = new Employee(1, "Test1", 23567.00, "Active", new
	 * Department(01, "IT", "Pune", "Active")); EmployeeVO employeeVO = new
	 * EmployeeVO(1, "Test1", 23567.00, "Active", 1); // Simulate saving a new
	 * employee
	 * Mockito.when(employeeRepository.save(employeeToSaveOrUpdate)).thenReturn(
	 * employeeToSaveOrUpdate);
	 * 
	 * // Simulate updating an existing employee
	 * Mockito.when(employeeRepository.existsById(employeeToSaveOrUpdate.getId())).
	 * thenReturn(true);
	 * 
	 * // Act: Save a new employee Employee savedEmployee =
	 * employeeService.saveOrUpdateEmployee(employeeVO);
	 * 
	 * // Assert: Verify the saved employee assertEquals(employeeToSaveOrUpdate,
	 * savedEmployee);
	 * 
	 * // Act: Update an existing employee
	 * employeeToSaveOrUpdate.setFirstName("Test2"); Employee updatedEmployee =
	 * employeeService.saveOrUpdateEmployee(employeeVO);
	 * 
	 * // Assert: Verify the updated employee assertEquals(employeeToSaveOrUpdate,
	 * updatedEmployee); }
	 */

}
