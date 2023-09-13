package com.krogerServiceB.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krogerServiceB.entity.DeleteEmployeeVO;
import com.krogerServiceB.entity.Department;
import com.krogerServiceB.entity.Employee;
import com.krogerServiceB.entity.EmployeeList;
import com.krogerServiceB.entity.EmployeeVO;
import com.krogerServiceB.exception.CommonExcaptionHandler;
import com.krogerServiceB.exception.EmployeeNotFoundBYIDException;
import com.krogerServiceB.exception.EmployeeNotFoundBYIDsException;
import com.krogerServiceB.exception.StatusIsActiveOrNot;
import com.krogerServiceB.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentService departmentService;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

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


	
	public DeleteEmployeeVO deleteEmployee(DeleteEmployeeVO employeeObj) {
		Employee employee = employeeRepository.getEmployeeByIdWithoutStatus(employeeObj.getId());
		// List<Employee> emp =
		// employeeRepository.getEmployeesByStatus(employeeObj.getStatus());
		String statuscode = employeeObj.getStatus();
		if ((employee != null)) {
			if (employeeObj.getStatus() != null
					&& (!employeeObj.getStatus().isBlank() && !employeeObj.getStatus().isEmpty())
					&& (employeeObj.getStatus().equalsIgnoreCase("Active")
							|| employeeObj.getStatus().equalsIgnoreCase("Inactive"))) {
				employee.setStatus(employeeObj.getStatus());
				employee = employeeRepository.save(employee);
			} else {
				throw new StatusIsActiveOrNot("Employee", "status", employeeObj.getStatus());
			}
		} else {
			throw new EmployeeNotFoundBYIDException("Employee", "ID", employeeObj.getId());
		}
		return employeeObj;
	}
	


	public EmployeeList getEmployeeByIds(List<Integer> employeeIds) {
		List<Employee> employees = employeeRepository.getEmployeeByIds(employeeIds);
		if (!employees.isEmpty()) {
			List<Employee> validEntities = new ArrayList<>();

			List<Integer> invalidEntities = new ArrayList<>();
			EmployeeList validInvalidIds = new EmployeeList();
			for (Integer id : employeeIds) {
				boolean found = false;
				for (Employee emp : employees) {
					if (emp.getId() == id) {
						validEntities.add(emp);
						found = true;
						break;
					}
				}
				if (!found) {
					invalidEntities.add(id);
				}
			}
			validInvalidIds.setEmpList(validEntities);
			if (!invalidEntities.isEmpty()) {
				validInvalidIds.setMsg(("Employee Id's not found " + invalidEntities));
			}
			return validInvalidIds;

		} else {
			throw new EmployeeNotFoundBYIDsException("Employee", "ID", employeeIds);
		}
	}

	@Override
	public Employee saveEmployeeService(EmployeeVO employeeVO) {
		Employee emp = null;
		if (employeeVO != null) {
			Department department = departmentService.getDepartmentById(employeeVO.getDepartmentId());
			if (department != null) {
				emp = new Employee();
				if (!employeeVO.getFirstName().isEmpty() && !employeeVO.getFirstName().isBlank()) {
					emp.setFirstName(employeeVO.getFirstName());
				} else {
					throw new CommonExcaptionHandler("Name");
				}
				if (employeeVO.getEmpSalary() != 0) {
					emp.setEmpSalary(employeeVO.getEmpSalary());
				} else {
					throw new CommonExcaptionHandler("Salary");
				}
				if (!employeeVO.getStatus().isEmpty() && !employeeVO.getStatus().isBlank() && employeeVO.getStatus().equalsIgnoreCase("Active") ) {
					emp.setStatus(employeeVO.getStatus());
				} else {
					throw new CommonExcaptionHandler("Status");
				}
				emp.setDepartment(department);
				emp = employeeRepository.save(emp);
			} else {
				throw new EmployeeNotFoundBYIDException("Department", "ID", employeeVO.getDepartmentId());
			}
		}
		return emp;
	}

	@Override
	public Employee updateEmployeeService(Employee employee) {
		Employee emp = null;
		emp = getEmployeeById(employee.getId());
		if (emp != null) {
			if (!employee.getFirstName().isEmpty() && !employee.getFirstName().isBlank()) {
				emp.setFirstName(employee.getFirstName());
			} else {
				throw new CommonExcaptionHandler("Name");
			}
			if (employee.getEmpSalary() != 0) {
				emp.setEmpSalary(employee.getEmpSalary());
			} else {
				throw new CommonExcaptionHandler("Salary");
			}
			if (!employee.getStatus().isEmpty() && !employee.getStatus().isBlank() && employee.getStatus().equalsIgnoreCase("Active") ) {
				emp.setStatus(employee.getStatus());
			} else {
				throw new CommonExcaptionHandler("Status");
			}
			emp = employeeRepository.save(emp);
		} else {
			throw new EmployeeNotFoundBYIDException("Employee", "ID", employee.getId());
		}
	return emp;
	}

	
	
	
	
//	@Override
//	public Employee saveOrUpdateEmployee(EmployeeVO employee) {
//		Employee emp = null;
//		if (employee.getId()!= 0) {
//			Department department = departmentService.getDepartmentById(employee.getDepartmentId());
//			if (department != null) {
//				emp = new Employee();
//				if (!employee.getFirstName().isEmpty() && !employee.getFirstName().isBlank()) {
//					emp.setFirstName(employee.getFirstName());
//				} else {
//					//throw new CommonExcaptionHandler("Please Provide Name", HttpStatus.BAD_REQUEST);
//				}
//
//				emp.setEmpSalary(employee.getEmpSalary());
//				emp.setStatus(employee.getStatus());
//				emp.setDepartment(department);
//				emp = employeeRepository.save(emp);
//			} else {
//				throw new EmployeeNotFoundBYIDException("Department", "ID", employee.getDepartmentId());
//			}
//		} else {
//			emp = getEmployeeById(employee.getId());
//			if (emp != null) {
//				if (employee.getFirstName() != null) {
//					emp.setFirstName(employee.getFirstName());
//				}
//				if (employee.getEmpSalary() != null) {
//					emp.setEmpSalary(employee.getEmpSalary());
//				}
//				if (employee.getStatus() != null) {
//					emp.setStatus(employee.getStatus());
//				}
//
//				emp = employeeRepository.save(emp);
//			} else {
//				throw new EmployeeNotFoundBYIDException("Employee", "ID", employee.getId());
//			}
//		}
//		return emp;
//	}

	
	
	
//	@Override
//	public Employee deleteEmployee(Employee employeeObj) {
//		Employee employee = employeeRepository.getEmployeeByIdWithoutStatus(employeeObj.getId());
//		if (employee != null) {
//			employee.setStatus(employeeObj.getStatus());
//			employee = employeeRepository.save(employee);
//		} else {
//			throw new EmployeeNotFoundBYIDException("Employee", "ID", employeeObj.getId());
//		}
//		return employee;
//	}
}
