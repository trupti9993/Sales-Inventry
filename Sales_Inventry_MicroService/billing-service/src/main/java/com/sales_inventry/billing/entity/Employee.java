package com.sales_inventry.billing.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sales_inventry.billing.dto.EmployeeDTO;

@Entity
public class Employee {

	@Id
	@Column(name = "emp_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer employeeId;

	@Column(name = "emp_name", nullable = false)
	String empName;

	@Column(name = "mobile_no", nullable = false)
	String mobileNo;

	@Column(name = "email", nullable = false)
	String email;

	@Column(name = "address", nullable = false)
	String address;

	@Column(name = "password", nullable = false)
	String password;

	@JsonManagedReference
	@OneToMany(mappedBy = "empId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Purchase> purchase;

	@JsonManagedReference
	@OneToMany(mappedBy = "empId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Sales> sales;

	@JsonManagedReference
	@OneToMany(mappedBy = "empId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Payment> payment;

	@JsonManagedReference
	@OneToMany(mappedBy = "empId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Receipt> receipt;

	public Employee(EmployeeDTO employeeDto) {
		this.setEmpName(employeeDto.getEmpName());
		this.setMobileNo(employeeDto.getMobileNo());
		this.setEmail(employeeDto.getEmail());
		this.setPassword(employeeDto.getPassword());
		this.setAddress(employeeDto.getAddress());
	}

	public Employee() {

	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}