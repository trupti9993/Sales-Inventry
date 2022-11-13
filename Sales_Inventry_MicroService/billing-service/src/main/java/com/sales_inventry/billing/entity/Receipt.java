package com.sales_inventry.billing.entity;

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
public class Receipt {

	@Id
	@Column(name = "receipt_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer receiptId;

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
	@JoinColumn(name = "sales_id", nullable = false)
	Sales salesId;

	@Column(name = "amount", nullable = false)
	Double amount;

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

	public Sales getSalesId() {
		return salesId;
	}

	public void setSalesId(Sales salesId) {
		this.salesId = salesId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Receipt() {

	}

}
