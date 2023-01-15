package com.sales_inventry.app.dto;

import com.sales_inventry.app.entities.Employee;

public class EmployeeDTO {

	Integer employeeId;

	String empName;

	String mobileNo;

	String email;

	String address;

	
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

	
	public EmployeeDTO(Employee emp) {
		this.setEmpName(emp.getEmpName());
		this.setMobileNo(emp.getMobileNo());
		this.setEmail(emp.getEmail());
		this.setAddress(emp.getAddress());
		this.setEmployeeId(emp.getEmployeeId());
	}

	public EmployeeDTO() {
	}

	@Override
	public String toString() {
		return "EmployeeDTO [employeeId=" + employeeId + ", empName=" + empName + ", mobileNo=" + mobileNo + ", email="
				+ email + ", address=" + address+ "]";
	}
	
	

}
