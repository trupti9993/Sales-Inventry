package com.sales_inventry.stock.service;

import java.util.List;

import com.sales_inventry.stock.dto.PurchaseDTO;

public interface PurchaseService {

	List<PurchaseDTO> getAllPurchaseDetails();

	void savePurchaseToDB(PurchaseDTO purchase);

	PurchaseDTO getPurchase(Integer purchaseId) throws Exception;

}
