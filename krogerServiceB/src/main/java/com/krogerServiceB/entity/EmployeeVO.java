package com.krogerServiceB.entity;

public class EmployeeVO {
//	private int id;
	private String firstName;
	private Double empSalary;
	private String status;
	private int departmentId;
	
	
//	public int getId() {
//		return id;
//	}
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
	public int getDepartmentId() {
		return departmentId;
	}
	
	
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public EmployeeVO(int id, String firstName, Double empSalary, String status, int departmentId, boolean flag) {
		super();
//		this.id = id;
		this.firstName = firstName;
		this.empSalary = empSalary;
		this.status = status;
		this.departmentId = departmentId;
		
	}
	
	
	
}
