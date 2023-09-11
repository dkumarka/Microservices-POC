package com.serviceA.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.serviceA.exception.EmployeeNotCreated;
import com.serviceA.exception.EmployeeNotFoundException;
import com.serviceA.exception.EmployeeNotUpdated;
import com.serviceA.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${dynamic.id.address.service.b}")
    String ip_address_get_b;

	public List<Employee> getAllEmployee(String token) {
		String Get_All_Emp_URL = ip_address_get_b + "/api/v1/getEmployees";
		ResponseEntity<Employee[]> response = restTemplate.exchange(Get_All_Emp_URL, HttpMethod.GET,
				createEntityForExchange(token), Employee[].class);
		return Arrays.asList(response.getBody());
	}

	public Employee getEmployeeById(Integer employeeId, String token) {
		String Get_Emp_By_Id_URL = ip_address_get_b + "/api/v1/getEmployee/";
		ResponseEntity<Employee> response = null;
		
		try {
			response = restTemplate.exchange(Get_Emp_By_Id_URL + employeeId, HttpMethod.GET,
					createEntityForExchange(token), Employee.class);
			return response.getBody();
		} catch (Exception e) {
			throw new EmployeeNotFoundException("Employee Not Present With Id " + employeeId);
		}
	}

	public ResponseEntity<Employee> createEmployee(Integer departmentId, Employee employee, String token)throws EmployeeNotCreated{
		String Create_Emp_URL = ip_address_get_b + "/api/v1/saveEmployee?departmentId=";
		ResponseEntity<Employee> response = null;
		try {
			response = restTemplate.exchange(Create_Emp_URL + departmentId, HttpMethod.POST,
					createEntityForPostExchange(employee, token), Employee.class);
			return response;
		}catch (Exception e) {
			throw new EmployeeNotCreated("Department Not Present With Id " + departmentId);
		}
		
	}

	public ResponseEntity<Employee> updateEmployee(Employee employee, Integer employeeId, String token)
			throws EmployeeNotUpdated {
		 String Update_Emp_URL = ip_address_get_b + "/api/v1/updateEmployee/";
		ResponseEntity<Employee> emp = null;
		try {
			emp = restTemplate.exchange(Update_Emp_URL + employeeId, HttpMethod.PUT,
					createEntityForPostExchange(employee, token), Employee.class);
			return emp;
		} catch (Exception e) {
			throw new EmployeeNotUpdated("Employee Not Present With Id " + employeeId);
		}
	}

	public String deleteEmployee(Integer employeeId, String token) {
		String Delete_Emp_By_Id_URL = ip_address_get_b + "/api/v1/deleteEmployee/";
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(Delete_Emp_By_Id_URL + employeeId, HttpMethod.DELETE,
					createEntityForExchange(token), String.class);
			return response.getBody();
		} catch (Exception e) {
			throw new EmployeeNotFoundException("Employee Not Present With Id " + employeeId);
		}
	}

	private HttpEntity<String> createEntityForExchange(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		return new HttpEntity<String>(headers);
	}

	private HttpEntity<Employee> createEntityForPostExchange(Employee emp, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		return new HttpEntity<Employee>(emp, headers);
	}

	/*
	 * @Override public ResponseEntity<Employee> updatePartially(Employee employee,
	 * Integer employeeId, String token) { ResponseEntity<Employee> emp =
	 * restTemplate.exchange(Patch_Emp_URL + employeeId, HttpMethod.PATCH,
	 * createEntityForPostExchange(employee, token), Employee.class); return emp; }
	 */
}
