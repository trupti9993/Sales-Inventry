package com.sales_inventry.billing.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Sales {

	@Id
	@Column(name = "sales_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer salesId;

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
	@JoinColumn(name = "prodId", nullable = false)
	Product prodId;

	@Column(name = "quantity", nullable = false)
	Double quantity;

	@Column(name = "rate", nullable = false)
	Double rate;

	@Column(name = "amount", nullable = false)
	Double amount;

	@Column(name = "discount", nullable = false)
	Double discount;

	@Column(name = "tax", nullable = false)
	Double tax;

	@Column(name = "net_amount", nullable = false)
	Double netAmount;

	@JsonManagedReference
	@OneToMany(mappedBy = "salesId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Receipt> receipt;

	public Integer getSalesId() {
		return salesId;
	}

	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
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

	public Product getProdId() {
		return prodId;
	}

	public void setProdId(Product prodId) {
		this.prodId = prodId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Set<Receipt> getReceipt() {
		return receipt;
	}

	public Sales() {

	}

}
