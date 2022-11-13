package com.sales_inventry.stock.entity;

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
import com.sales_inventry.stock.dto.ProductDTO;

@Entity
public class Product {

	@Id
	@Column(name = "prod_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer prodId;

	@Column(name = "prod_name", nullable = false)
	String prodName;

	@Column(name = "prod_type", nullable = false)
	String prodType;

	@Column(name = "prod_unit", nullable = false)
	String prodUnit;

	@Column(name = "no_of_decimals", nullable = false)
	Integer noOfDecimals;

	@JsonManagedReference
	@OneToMany(mappedBy = "prodId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Purchase> purchase;

	@JsonManagedReference
	@OneToMany(mappedBy = "prodId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Sales> sales;

	public Product(ProductDTO productDTO) {
		this.prodId = productDTO.getProdId();
		this.prodName = productDTO.getProdName();
		this.prodType = productDTO.getProdType();
		this.prodUnit = productDTO.getProdUnit();
		this.noOfDecimals = productDTO.getNoOfDecimals();
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

	public Set<Purchase> getPurchase() {
		return purchase;
	}

	public Set<Sales> getSales() {
		return sales;
	}

	public Product() {

	}

}
