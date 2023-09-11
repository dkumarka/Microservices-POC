package com.serviceA.model;

public class Employee {

	private int id;

	private String firstName;
	private Double empSalary;
	private String status;

	private Department department;

	public Employee() {
		super();
	}

	public Employee(int id, String firstName, Double empSalary, String status, Department department) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.empSalary = empSalary;
		this.status = status;
		this.department = department;
	}

	public Employee(int id, String firstName, Double empSalary, String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.empSalary = empSalary;
		this.status = status;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

}
