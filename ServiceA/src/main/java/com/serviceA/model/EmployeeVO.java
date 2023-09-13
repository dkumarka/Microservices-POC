package com.serviceA.model;

public class EmployeeVO {
	//private int id;
	private String firstName;
	private Double empSalary;
	private String status;
	private String departmentId;

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Double getEmpSalary() {
		return empSalary;
	}
	
	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}
