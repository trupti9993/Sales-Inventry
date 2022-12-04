package com.sales_inventry.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales_inventry.app.dto.ReceiptDTO;
import com.sales_inventry.app.dto.Response;
import com.sales_inventry.app.service.ReceiptService;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

	@Autowired
	ReceiptService receiptService;

	@PostMapping(value = "/saveReceipt")
	public ResponseEntity<Response> saveReceipt(@RequestBody ReceiptDTO receipt) {

		receiptService.saveReceiptToDB(receipt);

		Response response = new Response();
		response.setReceiptResponseData(receipt.getReceiptId());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/getReceipt/{receiptId}")
	public ResponseEntity<Response> getReceipt(@PathVariable("receiptId") Integer receiptId) throws Exception {

		ReceiptDTO receipt = receiptService.getReceipt(receiptId);

		Response response = new Response();
		response.setStatus(Response.SUCCESS_STATUS);
		response.setReceiptData(receipt);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getAllReceiptData")
	public ResponseEntity<Response> getAllReceiptDetails() {

		Response response = new Response();
		response.setReceiptResponseData(receiptService.getAllReciptsDetails());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@PostMapping("/deleteReceipt/{receiptId}")
	public ResponseEntity<Response> deleteReceipt(@PathVariable("receiptId") Integer receiptId) throws Exception {

		Integer result = receiptService.deleteReceipt(receiptId);

		Response response = new Response();
		response.setStatus(result == 0 ? Response.FAIL_STATUS : Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);
	}

}