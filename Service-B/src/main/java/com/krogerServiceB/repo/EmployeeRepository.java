package com.krogerServiceB.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.krogerServiceB.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT emp FROM Employee emp WHERE emp.id=:id and emp.status='Active'")
	Employee getEmployeeById(int id);

	@Query("SELECT emp FROM Employee emp WHERE emp.status =:status")
	List<Employee> getEmployeesByStatus(String status);

}