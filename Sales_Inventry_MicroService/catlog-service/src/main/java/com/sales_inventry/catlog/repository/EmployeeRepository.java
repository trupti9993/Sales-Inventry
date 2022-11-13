package com.sales_inventry.catlog.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sales_inventry.catlog.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Cacheable(value = "empCache", key = "#empId", condition = "#empId!=null")
	Optional<Employee> findByEmployeeId(Integer empId);

}
