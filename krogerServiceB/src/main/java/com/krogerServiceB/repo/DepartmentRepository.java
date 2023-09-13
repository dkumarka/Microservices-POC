package com.krogerServiceB.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krogerServiceB.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	Department getDepartmentById(int id);

}
