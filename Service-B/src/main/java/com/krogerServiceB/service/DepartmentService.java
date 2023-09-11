package com.krogerServiceB.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krogerServiceB.entity.Department;

@Service
public interface DepartmentService {

	Department saveDepartment(Department department);

	List<Department> getDepartments();

	Department getDepartmentById(int id);

} 