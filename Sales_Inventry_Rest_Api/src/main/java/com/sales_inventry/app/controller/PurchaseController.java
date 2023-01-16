package com.sales_inventry.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales_inventry.app.dto.PurchaseDTO;
import com.sales_inventry.app.dto.Response;
import com.sales_inventry.app.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;

	@PostMapping(value = "/savePurchase")
	public ResponseEntity<Response> savePurchase(@RequestBody PurchaseDTO purchase) {

		purchaseService.savePurchaseToDB(purchase);

		Response response = new Response();
		response.setPurchaseResponseData(purchase.getPurchaseId());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/getPurchase/{purchaseId}")
	public ResponseEntity<Response> getPurchase(@PathVariable("purchaseId") Integer purchaseId) throws Exception {

		PurchaseDTO purchase = purchaseService.getPurchase(purchaseId);

		Response response = new Response();
		response.setStatus(Response.SUCCESS_STATUS);
		response.setPurchaseData(purchase);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getAllPurchaseData")
	public ResponseEntity<Response> getAllPurchaseDetails() {

		Response response = new Response();
		response.setPurchaseResponseData(purchaseService.getAllPurchaseDetails());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/getAllPurchaseDataForPayment")
	public ResponseEntity<Response> getAllPurchaseDetailsForPayment() {

		Response response = new Response();
		response.setPurchaseResponseData(purchaseService.getAllPurchaseDetailsForPayment());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@PostMapping("/deletePurchase/{purchaseId}")
	public ResponseEntity<Response> deletePurchase(@PathVariable("purchaseId") Integer purchaseId) throws Exception {

		Integer result = purchaseService.deletePurchase(purchaseId);

		Response response = new Response();
		response.setStatus(result == 0 ? Response.FAIL_STATUS : Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);
	}

}
