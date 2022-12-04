package com.sales_inventry.springclient.model;

public class ProductDTO {

	Integer prodId;

	String prodName;

	String prodType;

	String prodUnit;

	Integer noOfDecimals;

	public ProductDTO() {
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getProdUnit() {
		return prodUnit;
	}

	public void setProdUnit(String prodUnit) {
		this.prodUnit = prodUnit;
	}

	public Integer getNoOfDecimals() {
		return noOfDecimals;
	}

	public void setNoOfDecimals(Integer noOfDecimals) {
		this.noOfDecimals = noOfDecimals;
	}

}
