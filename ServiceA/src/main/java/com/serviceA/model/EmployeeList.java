package com.serviceA.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class EmployeeList {
	
	
	List<Employee> empList;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String msg;
	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
