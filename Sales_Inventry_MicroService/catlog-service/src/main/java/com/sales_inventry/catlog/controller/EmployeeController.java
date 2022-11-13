package com.sales_inventry.catlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales_inventry.catlog.dto.EmployeeDTO;
import com.sales_inventry.catlog.dto.Response;
import com.sales_inventry.catlog.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping(value = "/saveEmployee")
	public ResponseEntity<Response> saveEmployee(@RequestBody EmployeeDTO employee) {

		employeeService.saveEmployeeToDB(employee);

		Response response = new Response();
		response.setResponseData(employee.getEmployeeId());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/getEmployee/{empId}")
	public ResponseEntity<Response> saveEmployeesToDB(@PathVariable("empId") Integer empId) throws Exception {

		EmployeeDTO emp = employeeService.getEmployee(empId);

		Response response = new Response();
		response.setStatus(Response.SUCCESS_STATUS);
		response.setResponseData(emp);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getAllEmployeeData")
	public ResponseEntity<Response> getAllEmpDetails() {

		Response response = new Response();
		response.setResponseData(employeeService.getAllEmployeeDetails());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

}