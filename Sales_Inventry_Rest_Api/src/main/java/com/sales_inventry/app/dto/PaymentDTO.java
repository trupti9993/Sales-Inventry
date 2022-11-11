package com.sales_inventry.app.dto;

import java.util.Date;

import com.sales_inventry.app.entities.Payment;

public class PaymentDTO {

	Integer paymentId;

	Date date;

	Integer empId;

	String empName;

	Integer partyId;

	String partyName;

	Integer purchaseId;

	Double amount;

	public PaymentDTO(Payment payment) {

		this.paymentId = payment.getPaymentId();

		this.date = payment.getDate();

		this.empId = payment.getEmpId().getEmployeeId();

		this.empName = payment.getEmpId().getEmpName();

		this.partyId = payment.getPartyId().getPartyId();

		this.partyName = payment.getPartyId().getPartyName();

		this.amount = payment.getAmount();

		this.purchaseId = payment.getPurchaseId().getPurchaseId();
	}

	public PaymentDTO() {
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
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

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
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
