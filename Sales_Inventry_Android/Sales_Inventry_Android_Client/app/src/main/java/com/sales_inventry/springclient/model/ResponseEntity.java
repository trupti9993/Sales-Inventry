package com.sales_inventry.springclient.model;

import java.util.List;

public class ResponseEntity {

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<EmployeeDTO> getEmpResponseData() {
		return empResponseData;
	}

	public void setEmpResponseData(List<EmployeeDTO> empResponseData) {
		this.empResponseData = empResponseData;
	}

	public static String SUCCESS_STATUS = "SUCCESS";

	public static String FAIL_STATUS = "FAIL";

	private String status;

	private List<EmployeeDTO> empResponseData;

	private EmployeeDTO empData;

	public EmployeeDTO getEmpData() {
		return empData;
	}

	public void setEmpData(EmployeeDTO empData) {
		this.empData = empData;
	}
}
