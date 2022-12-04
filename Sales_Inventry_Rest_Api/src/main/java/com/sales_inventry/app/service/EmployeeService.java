package com.sales_inventry.app.service;

import java.util.List;

import com.sales_inventry.app.dto.EmployeeDTO;

public interface EmployeeService {

	List<EmployeeDTO> getAllEmployeeDetails();

	void saveEmployeeToDB(EmployeeDTO employee);

	EmployeeDTO getEmployee(Integer employeeId) throws Exception;
	
	Integer deleteEmployee(Integer employeeId) throws Exception;

}
