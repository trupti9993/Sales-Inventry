package com.sales_inventry.stock.dto;

import com.sales_inventry.stock.entity.Employee;

public class EmployeeDTO {

	Integer employeeId;

	String empName;

	String mobileNo;

	String email;

	String address;

	String password;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public String getEmpName() {
		return empName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EmployeeDTO(Employee emp) {
		this.setEmpName(emp.getEmpName());
		this.setMobileNo(emp.getMobileNo());
		this.setEmail(emp.getEmail());
		this.setPassword(emp.getPassword());
		this.setAddress(emp.getAddress());
	}

	public EmployeeDTO() {
	}

}
