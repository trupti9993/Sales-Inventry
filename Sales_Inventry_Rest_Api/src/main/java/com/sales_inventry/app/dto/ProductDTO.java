package com.sales_inventry.app.dto;

import com.sales_inventry.app.entities.Product;

public class ProductDTO {

	Integer prodId;

	String prodName;

	String prodType;

	String prodUnit;

	Integer stock;

	public ProductDTO(Product product) {
		this.prodId = product.getProdId();
		this.prodName = product.getProdName();
		this.prodType = product.getProdType();
		this.prodUnit = product.getProdUnit();
	}

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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
