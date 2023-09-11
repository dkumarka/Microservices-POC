package com.krogerServiceB;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.krogerServiceB.entity.Department;
import com.krogerServiceB.repo.DepartmentRepository;
import com.krogerServiceB.service.DepartmentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestDepartmentController {
	
	@Autowired
	private DepartmentServiceImpl departmentService;

	@Mock
	private DepartmentRepository departmentRepository;

	private Department department;

	@BeforeEach
	void setUp() {
		this.departmentService = new DepartmentServiceImpl(this.departmentRepository);
	}

	@DisplayName("JUnit test for get all department operation")
	@Test
	public void getDepartmentsTest() {
		departmentService.getDepartments();
		verify(departmentRepository).findAll();
	}
}
