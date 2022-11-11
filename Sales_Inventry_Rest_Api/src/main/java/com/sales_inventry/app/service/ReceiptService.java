package com.sales_inventry.app.service;

import java.util.List;

import com.sales_inventry.app.dto.ReceiptDTO;

public interface ReceiptService {

	List<ReceiptDTO> getAllReciptsDetails();

	void saveReceiptToDB(ReceiptDTO receipt);

	ReceiptDTO getReceipt(Integer receiptId) throws Exception;

}
