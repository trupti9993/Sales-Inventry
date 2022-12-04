package com.sales_inventry.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales_inventry.app.dto.EmployeeDTO;
import com.sales_inventry.app.entities.Employee;
import com.sales_inventry.app.repository.EmployeeRepository;
import com.sales_inventry.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeDTO> getAllEmployeeDetails() {
		List<Employee> empList = (List<Employee>) employeeRepository.findAll();
		List<EmployeeDTO> empDtoList = new ArrayList<EmployeeDTO>();

		for (Employee emp : empList) {
			empDtoList.add(new EmployeeDTO(emp));
		}

		return empDtoList;
	}

	@Override
	public void saveEmployeeToDB(EmployeeDTO employeeDto) {
		employeeRepository.save(new Employee(employeeDto));
	}

	@Override
	public EmployeeDTO getEmployee(Integer employeeId) throws Exception {

		Optional<Employee> employee = employeeRepository.findByEmployeeId(employeeId);

		if (!employee.isPresent()) {
			throw new Exception("No Data Found..!");
		}

		return new EmployeeDTO(employee.get());
	}

	@Override
	public Integer deleteEmployee(Integer employeeId) throws Exception {
		return employeeRepository.deleteEmployee(employeeId);
	}

}