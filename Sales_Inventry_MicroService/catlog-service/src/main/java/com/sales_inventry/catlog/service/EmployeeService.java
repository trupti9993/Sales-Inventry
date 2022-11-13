package com.sales_inventry.catlog.service;

import java.util.List;

import com.sales_inventry.catlog.dto.EmployeeDTO;

public interface EmployeeService {

	List<EmployeeDTO> getAllEmployeeDetails();

	void saveEmployeeToDB(EmployeeDTO employee);

	EmployeeDTO getEmployee(Integer employeeId) throws Exception;

}
