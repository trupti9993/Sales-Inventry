package com.sales_inventry.billing.service;

import java.util.List;

import com.sales_inventry.billing.dto.ReceiptDTO;

public interface ReceiptService {

	List<ReceiptDTO> getAllReciptsDetails();

	void saveReceiptToDB(ReceiptDTO receipt);

	ReceiptDTO getReceipt(Integer receiptId) throws Exception;

}
