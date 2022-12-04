package com.sales_inventry.springclient.model;

import java.util.List;

public class EmployeeResponseEntity {

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<EmployeeDTO> getResponseData() {
		return responseData;
	}

	public void setResponseData(List<EmployeeDTO> responseData) {


		this.responseData = responseData;
	}

	public static String SUCCESS_STATUS = "SUCCESS";

	public static String FAIL_STATUS = "FAIL";

	private String status;

	private List<EmployeeDTO> responseData;

	private EmployeeDTO empData;

	public EmployeeDTO getEmpData() {
		return empData;
	}

	public void setEmpData(EmployeeDTO empData) {
		this.empData = empData;
	}
}