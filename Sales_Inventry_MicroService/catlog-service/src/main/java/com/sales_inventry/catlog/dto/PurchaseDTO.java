package com.sales_inventry.catlog.dto;

import java.util.Date;

import com.sales_inventry.catlog.entity.Purchase;

public class PurchaseDTO {

	Integer purchaseId;

	Date date;

	Integer empId;

	String empName;

	Integer partyId;

	String partyName;

	Integer prodId;

	String prodName;

	Double quantity;

	Double rate;

	Double amount;

	Double discount;

	Double tax;

	Double netAmount;

	public PurchaseDTO(Purchase purchase) {
		this.purchaseId = purchase.getPurchaseId();

		this.date = purchase.getDate();

		this.empId = purchase.getEmpId().getEmployeeId();
		this.empName = purchase.getEmpId().getEmpName();

		this.partyId = purchase.getPartyId().getPartyId();
		this.partyName = purchase.getPartyId().getPartyName();

		this.prodId = purchase.getProdId().getProdId();
		this.prodName = purchase.getProdId().getProdName();

		this.quantity = purchase.getQuantity();

		this.rate = purchase.getRate();

		this.amount = purchase.getAmount();

		this.discount = purchase.getDiscount();

		this.tax = purchase.getTax();

		this.netAmount = purchase.getNetAmount();
	}

	public PurchaseDTO() {
	}

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
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

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
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

}
