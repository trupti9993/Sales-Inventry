package com.sales_inventry.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales_inventry.stock.dto.PurchaseDTO;
import com.sales_inventry.stock.dto.Response;
import com.sales_inventry.stock.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;

	@PostMapping(value = "/savePurchase")
	public ResponseEntity<Response> saveEmployee(@RequestBody PurchaseDTO purchase) {

		purchaseService.savePurchaseToDB(purchase);

		Response response = new Response();
		response.setResponseData(purchase.getPurchaseId());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/getPurchase/{purchaseId}")
	public ResponseEntity<Response> saveEmployeesToDB(@PathVariable("purchaseId") Integer purchaseId) throws Exception {

		PurchaseDTO purchase = purchaseService.getPurchase(purchaseId);

		Response response = new Response();
		response.setStatus(Response.SUCCESS_STATUS);
		response.setResponseData(purchase);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getAllPurchaseData")
	public ResponseEntity<Response> getAllPurchaseDetails() {

		Response response = new Response();
		response.setResponseData(purchaseService.getAllPurchaseDetails());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

}