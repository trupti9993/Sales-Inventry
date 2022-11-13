package com.sales_inventry.catlog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales_inventry.catlog.dto.EmployeeDTO;
import com.sales_inventry.catlog.entity.Employee;
import com.sales_inventry.catlog.repository.EmployeeRepository;
import com.sales_inventry.catlog.service.EmployeeService;

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

}