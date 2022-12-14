package com.sales_inventry.app.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.sales_inventry.app.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Cacheable(value = "empCache", key = "#empId", condition = "#empId!=null")
	Optional<Employee> findByEmployeeId(Integer empId);
	
	@Procedure(procedureName = "deleteEmployee", outputParameterName = "status")
	Integer deleteEmployee(@Param("empId") Integer empId);

}
