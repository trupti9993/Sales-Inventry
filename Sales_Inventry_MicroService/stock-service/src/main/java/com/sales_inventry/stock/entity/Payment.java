package com.sales_inventry.stock.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Payment {

	@Id
	@Column(name = "payment_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer paymentId;

	@Column(name = "date", nullable = false)
	Date date;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "emp_id", nullable = false)
	Employee empId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "party_id", nullable = false)
	Party partyId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "purchase_id", nullable = false)
	Purchase purchaseId;

	@Column(name = "amount", nullable = false)
	Double amount;

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

	public Employee getEmpId() {
		return empId;
	}

	public void setEmpId(Employee empId) {
		this.empId = empId;
	}

	public Party getPartyId() {
		return partyId;
	}

	public void setPartyId(Party partyId) {
		this.partyId = partyId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Purchase getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Purchase purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Payment() {

	}

}
