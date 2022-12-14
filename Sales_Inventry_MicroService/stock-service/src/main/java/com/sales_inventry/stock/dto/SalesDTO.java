package com.sales_inventry.stock.dto;

import java.util.Date;

import com.sales_inventry.stock.entity.Sales;

public class SalesDTO {

	Integer salesId;

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

	public SalesDTO(Sales sale) {

		this.salesId = sale.getSalesId();

		this.date = sale.getDate();

		this.empId = sale.getEmpId().getEmployeeId();
		this.empName = sale.getEmpId().getEmpName();

		this.partyId = sale.getPartyId().getPartyId();
		this.partyName = sale.getPartyId().getPartyName();

		this.prodId = sale.getProdId().getProdId();
		this.prodName = sale.getProdId().getProdName();

		this.quantity = sale.getQuantity();

		this.rate = sale.getRate();

		this.amount = sale.getAmount();

		this.discount = sale.getDiscount();

		this.tax = sale.getTax();

		this.netAmount = sale.getNetAmount();

	}

	public SalesDTO() {

	}

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
