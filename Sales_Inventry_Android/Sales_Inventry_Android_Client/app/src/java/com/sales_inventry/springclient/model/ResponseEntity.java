package com.sales_inventry.springclient.model;

import java.util.List;

public class ResponseEntity {

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static String SUCCESS_STATUS = "SUCCESS";

	public static String FAIL_STATUS = "FAIL";

	private String status;

	private List<EmployeeDTO> empResponseData;

	public List<EmployeeDTO> getEmpResponseData() {
		return empResponseData;
	}

	public void setEmpResponseData(List<EmployeeDTO> empResponseData) {
		this.empResponseData = empResponseData;
	}

	private EmployeeDTO empData;

	public EmployeeDTO getEmpData() {
		return empData;
	}

	public void setEmpData(EmployeeDTO empData) {
		this.empData = empData;
	}

	private List<PartyDTO> partyResponseData;

	public List<PartyDTO> getPartyResponseData() {
		return partyResponseData;
	}

	public void setPartyResponseData(List<PartyDTO> partyResponseData) {
		this.partyResponseData = partyResponseData;
	}

	private PartyDTO partyData;

	public PartyDTO getPartyData() {
		return partyData;
	}

	public void setPartyData(PartyDTO partyData) {
		this.partyData = partyData;
	}

	private List<ProductDTO> productResponseData;

	public List<ProductDTO> getProductResponseData() {
		return productResponseData;
	}

	public void setProductResponseData(List<ProductDTO> productResponseData) {
		this.productResponseData = productResponseData;
	}

	private ProductDTO productData;

	public ProductDTO getProductData() {
		return productData;
	}

	public void setProductData(ProductDTO productData) {
		this.productData = productData;
	}

	private List<PurchaseDTO> purchaseResponseData;

	public List<PurchaseDTO> getPurchaseResponseData() {
		return purchaseResponseData;
	}

	public void setPurchaseResponseData(List<PurchaseDTO> purchaseResponseData) {
		this.purchaseResponseData = purchaseResponseData;
	}


	private PurchaseDTO purchaseData;

	public PurchaseDTO getPurchaseData() {
		return purchaseData;
	}

	public void setPurchaseData(PurchaseDTO purchaseData) {
		this.purchaseData = purchaseData;
	}

	private List<PaymentDTO> paymentResponseData;

	public List<PaymentDTO> getPaymentResponseData() {
		return paymentResponseData;
	}

	public void setPaymentResponseData(List<PaymentDTO> paymentResponseData) {
		this.paymentResponseData = paymentResponseData;
	}


	private PaymentDTO paymentData;

	public PaymentDTO getPaymentData() {
		return paymentData;
	}

	public void setPaymentData(PaymentDTO paymentData) {
		this.paymentData = paymentData;
	}

	private List<ReceiptDTO> receiptResponseData;

	public List<ReceiptDTO> getReceiptResponseData() {
		return receiptResponseData;
	}

	public void setReceiptResponseData(List<ReceiptDTO> receiptResponseData) {
		this.receiptResponseData = receiptResponseData;
	}


	private ReceiptDTO receiptData;

	public ReceiptDTO getReceiptData() {
		return receiptData;
	}

	public void setReceiptData(ReceiptDTO receiptData) {
		this.receiptData = receiptData;
	}

	private List<SalesDTO> saleResponseData;

	public List<SalesDTO> getSaleResponseData() {
		return saleResponseData;
	}

	public void setSaleResponseData(List<SalesDTO> saleResponseData) {
		this.saleResponseData = saleResponseData;
	}


	private SalesDTO saleData;

	public SalesDTO getSaleData() {
		return saleData;
	}

	public void setSaleData(SalesDTO saleData) {
		this.saleData = saleData;
	}

}
