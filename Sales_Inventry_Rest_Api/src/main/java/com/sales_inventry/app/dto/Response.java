package com.sales_inventry.app.dto;

import org.springframework.http.HttpStatus;

import com.sales_inventry.app.exception.ErrorDetails;

public class Response {

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ErrorDetails getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(ErrorDetails errorDetails) {
		this.errorDetails = errorDetails;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public static String SUCCESS_STATUS = "SUCCESS";

	public static String FAIL_STATUS = "FAIL";

	private String status;

	private Object empResponseData;
	
	private Object partyResponseData;
	
	private Object productResponseData;
	
	private Object purchaseResponseData;
	
	private Object saleResponseData;
	
	private Object paymentResponseData;
	
	private Object receiptResponseData;

	private Object empData;
	
	private Object partyData;
	
	private Object productData;
	
	private Object purchaseData;
	
	private Object saleData;
	
	private Object paymentData;
	
	private Object receiptData;

	private ErrorDetails errorDetails;

	private HttpStatus httpStatus;

	public Object getEmpData() {
		return empData;
	}

	public void setEmpData(Object empData) {
		this.empData = empData;
	}

	public Object getEmpResponseData() {
		return empResponseData;
	}

	public void setEmpResponseData(Object empResponseData) {
		this.empResponseData = empResponseData;
	}

	public Object getPartyResponseData() {
		return partyResponseData;
	}

	public void setPartyResponseData(Object partyResponseData) {
		this.partyResponseData = partyResponseData;
	}

	public Object getProductResponseData() {
		return productResponseData;
	}

	public void setProductResponseData(Object productResponseData) {
		this.productResponseData = productResponseData;
	}

	public Object getPurchaseResponseData() {
		return purchaseResponseData;
	}

	public void setPurchaseResponseData(Object purchaseResponseData) {
		this.purchaseResponseData = purchaseResponseData;
	}

	public Object getSaleResponseData() {
		return saleResponseData;
	}

	public void setSaleResponseData(Object saleResponseData) {
		this.saleResponseData = saleResponseData;
	}

	public Object getPaymentResponseData() {
		return paymentResponseData;
	}

	public void setPaymentResponseData(Object paymentResponseData) {
		this.paymentResponseData = paymentResponseData;
	}

	public Object getReceiptResponseData() {
		return receiptResponseData;
	}

	public void setReceiptResponseData(Object receiptResponseData) {
		this.receiptResponseData = receiptResponseData;
	}

	public Object getPartyData() {
		return partyData;
	}

	public void setPartyData(Object partyData) {
		this.partyData = partyData;
	}

	public Object getProductData() {
		return productData;
	}

	public void setProductData(Object productData) {
		this.productData = productData;
	}

	public Object getPurchaseData() {
		return purchaseData;
	}

	public void setPurchaseData(Object purchaseData) {
		this.purchaseData = purchaseData;
	}

	public Object getSaleData() {
		return saleData;
	}

	public void setSaleData(Object saleData) {
		this.saleData = saleData;
	}

	public Object getPaymentData() {
		return paymentData;
	}

	public void setPaymentData(Object paymentData) {
		this.paymentData = paymentData;
	}

	public Object getReceiptData() {
		return receiptData;
	}

	public void setReceiptData(Object receiptData) {
		this.receiptData = receiptData;
	}

	
}
