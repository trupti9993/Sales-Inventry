package com.sales_inventry.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales_inventry.app.dto.EmployeeDTO;
import com.sales_inventry.app.dto.Response;
import com.sales_inventry.app.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping(value = "/saveEmployee")
	public ResponseEntity<Response> saveEmployee(@RequestBody EmployeeDTO employee) {

		employeeService.saveEmployeeToDB(employee);

		Response response = new Response();
		response.setEmpResponseData(employee.getEmployeeId());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/getEmployee/{empId}")
	public ResponseEntity<Response> getEmployee(@PathVariable("empId") Integer empId) throws Exception {

		EmployeeDTO emp = employeeService.getEmployee(empId);

		Response response = new Response();
		response.setStatus(Response.SUCCESS_STATUS);
		response.setEmpData(emp);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getAllEmployeeData")
	public ResponseEntity<Response> getAllEmpDetails() {

		Response response = new Response();
		response.setEmpResponseData(employeeService.getAllEmployeeDetails());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@PostMapping("/deleteEmployee/{empId}")
	public ResponseEntity<Response> deleteEmployee(@PathVariable("empId") Integer empId) throws Exception {

		Integer result = employeeService.deleteEmployee(empId);

		Response response = new Response();
		response.setStatus(result == 0 ? Response.FAIL_STATUS : Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);
	}

}