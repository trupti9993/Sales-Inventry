package com.sales_inventry.app.service;

import java.util.List;

import com.sales_inventry.app.dto.PurchaseDTO;

public interface PurchaseService {

	List<PurchaseDTO> getAllPurchaseDetails();

	void savePurchaseToDB(PurchaseDTO purchase);

	PurchaseDTO getPurchase(Integer purchaseId) throws Exception;

}
