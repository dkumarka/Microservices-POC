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
import com.serviceA.exception.StatusIsActiveOrNot;
import com.serviceA.model.DeleteEmployeeVO;
import com.serviceA.model.Employee;
import com.serviceA.model.EmployeeList;
import com.serviceA.model.EmployeeVO;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${dynamic.id.address.service.b}")
	String ip_address_get_b;

	public List<Employee> getAllEmployee(String token) {
		String Get_All_Emp_URL = ip_address_get_b + "/service-b/api/v1/getEmployees";
		ResponseEntity<Employee[]> response = restTemplate.exchange(Get_All_Emp_URL, HttpMethod.GET,
				createEntityForExchange(token), Employee[].class);
		return Arrays.asList(response.getBody());
	}


	public EmployeeList getEmployeeByEmpIds(String employeeIds, String token) {
		String Get_Emp_By_Id_URL = ip_address_get_b + "/service-b/api/v1/getEmployeeById/" + employeeIds;
		ResponseEntity<EmployeeList> response = null;
		try {
			response = restTemplate.exchange(Get_Emp_By_Id_URL, HttpMethod.GET, createEntityForExchange(token),
					EmployeeList.class);

			return response.getBody();

		} catch (Exception e) {
			throw new EmployeeNotFoundException("Employee Not Present With Id " + employeeIds);
		}
	}
	
	public ResponseEntity<Employee> createEmployee(EmployeeVO employee, String token) throws EmployeeNotCreated {
		String Create_Emp_URL = ip_address_get_b + "/service-b/api/v1/saveEmployee";
		ResponseEntity<Employee> response = null;
		try {
			response = restTemplate.exchange(Create_Emp_URL, HttpMethod.POST,
					createEntityForPostExchange(employee, token), Employee.class);
			return response;
		} catch (Exception e) {
			throw new EmployeeNotCreated("Department Not Present With Id " + employee.getDepartmentId());
		}
	}
	public ResponseEntity<Employee> updateEmployee(Employee employee, String token) throws EmployeeNotUpdated {
		String Update_Emp_URL = ip_address_get_b + "/service-b/api/v1/updateEmployee";
		ResponseEntity<Employee> emp = null;
		try {
			emp = restTemplate.exchange(Update_Emp_URL, HttpMethod.PUT, createEntityForPutExchange(employee, token),
					Employee.class);
			return emp;
		} catch (Exception e) {
			throw new EmployeeNotUpdated("Employee Not Present With Id " + employee.getId());
		}
	}
	private HttpEntity<String> createEntityForExchange(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		return new HttpEntity<String>(headers);
	}

	private HttpEntity<DeleteEmployeeVO> createEntityForPostExchange(DeleteEmployeeVO employee, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		return new HttpEntity<DeleteEmployeeVO>(employee, headers);
	}
	private HttpEntity<EmployeeVO> createEntityForPostExchange(EmployeeVO employee, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		return new HttpEntity<EmployeeVO>(employee, headers);
	}
	private HttpEntity<Employee> createEntityForPutExchange(Employee employee, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		return new HttpEntity<Employee>(employee, headers);
	}

	public String deleteEmployee(DeleteEmployeeVO employee, String token) {
		String Delete_Emp_By_Id_URL = ip_address_get_b + "/service-b/api/v1/deleteEmployee";
		ResponseEntity<String> response = null;
		if ((employee != null) && (employee.getId() != 0)) {
			 if(employee.getStatus()!= null && (!employee.getStatus().isBlank() && !employee.getStatus().isEmpty())
					 && (employee.getStatus().equalsIgnoreCase("Active") || employee.getStatus().equalsIgnoreCase("InActive"))) {
			   try {
				 response = restTemplate.exchange(Delete_Emp_By_Id_URL, HttpMethod.DELETE,
					createEntityForPostExchange(employee, token), String.class);
			        return response.getBody();
			   }catch(Exception e) {
				   throw new EmployeeNotFoundException("Employee Not Present With Id " + employee.getId());
			   }} 
			 else {
			throw new StatusIsActiveOrNot("Please provide the valid status " + employee.getStatus());
		}
		}
		else {
			throw new EmployeeNotFoundException("Employee Not Present With Id " + employee.getId());
		}
	}
	
	

		
	/*
	 * public ResponseEntity<Employee> saveOrUpdateEmployee(EmployeeVO employee,
	 * String token) throws EmployeeNotCreated { String saveOrUpdate_Emp_URL =
	 * ip_address_get_b + "/service-b/api/v1/saveOrUpdateEmployee";
	 * ResponseEntity<Employee> emp = null; try { emp =
	 * restTemplate.exchange(saveOrUpdate_Emp_URL, HttpMethod.POST,
	 * createEntityForPostOrUpdateExchange(employee, token), Employee.class); return
	 * emp; } catch (Exception e) { throw new
	 * EmployeeNotCreated("Employee Not Present With Id " + employee.getId()); } }
	 */
//	@Override
//	public ResponseEntity<Employee> saveOrUpdateEmployee(EmployeeVO employee, String token) throws EmployeeNotCreated {
//		String saveOrUpdate_Emp_URL = ip_address_get_b + "/service-b/api/v1/saveOrUpdateEmployee";
//		ResponseEntity<Employee> emp = null;
//		if (employee.getId() == 0) {
//			if (employee.getDepartmentId() != null) {
//				try {
//					emp = restTemplate.exchange(saveOrUpdate_Emp_URL, HttpMethod.POST,
//							createEntityForPostOrUpdateExchange(employee, token), Employee.class);
//					return emp;
//				} catch (Exception e) {
//					throw new EmployeeNotCreated("Department Not Present With Id " + employee.getDepartmentId());
//				}
//			}
//		} else {
//			try {
//				emp = restTemplate.exchange(saveOrUpdate_Emp_URL, HttpMethod.POST,
//						createEntityForPostOrUpdateExchange(employee, token), Employee.class);
//				return emp;
//			} catch (Exception e) {
//				throw new EmployeeNotCreated("Employee Not Present With Id " + employee.getId());
//			}
//		}
//		return emp;
//	}
	
	//
//	public String deleteEmployee(DeleteEmployeeVO employee, String token) {
//		String Delete_Emp_By_Id_URL = ip_address_get_b + "/service-b/api/v1/deleteEmployee";
//		ResponseEntity<String> response = null;
//		try {
//			response = restTemplate.exchange(Delete_Emp_By_Id_URL, HttpMethod.DELETE,
//					createEntityForPostExchange(employee, token), String.class);
//			return response.getBody();
//		} catch (Exception e) {
//			throw new EmployeeNotFoundException("Employee Not Present With Id " + employee.getId());
//		}
//	}

	
	//
//	public List<Employee> getEmployeeByIds(String employeeId, String token) {
//		String Get_Emp_By_Id_URL = ip_address_get_b + "/service-b/api/v1/getEmployeeById/" + employeeId;
//		ResponseEntity<EmployeeList> response = null;
//		try {
//			System.out.println("Inside trys");
//			response = restTemplate.exchange(Get_Emp_By_Id_URL, HttpMethod.GET, createEntityForExchange(token),
//					EmployeeList.class);
//
//			return null;
//
//		} catch (Exception e) {
//			throw new EmployeeNotFoundException("Employee Not Present With Id " + employeeId);
//		}
//	}
}
