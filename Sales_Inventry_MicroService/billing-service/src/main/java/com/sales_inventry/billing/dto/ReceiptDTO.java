package com.sales_inventry.billing.dto;

import java.util.Date;

import com.sales_inventry.billing.entity.Receipt;

public class ReceiptDTO {

	Integer receiptId;

	Date date;

	Integer empId;

	String empName;

	Integer partyId;

	String partyName;

	Integer salesId;

	Double amount;

	public ReceiptDTO(Receipt receipt) {

		this.receiptId = receipt.getReceiptId();

		this.date = receipt.getDate();

		this.empId = receipt.getEmpId().getEmployeeId();

		this.empName = receipt.getEmpId().getEmpName();

		this.partyId = receipt.getPartyId().getPartyId();

		this.partyName = receipt.getPartyId().getPartyName();

		this.amount = receipt.getAmount();

		this.salesId = receipt.getSalesId().getSalesId();
	}

	public ReceiptDTO() {

	}

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public Integer getSalesId() {
		return salesId;
	}

	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getEmpName() {
		return empName;
	}

	public String getPartyName() {
		return partyName;
	}

}
