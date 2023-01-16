package com.sales_inventry.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales_inventry.app.dto.Response;
import com.sales_inventry.app.dto.SalesDTO;
import com.sales_inventry.app.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {

	@Autowired
	SalesService salesService;

	@PostMapping(value = "/saveSales")
	public ResponseEntity<Response> saveSale(@RequestBody SalesDTO sales) {

		salesService.saveSalesToDB(sales);

		Response response = new Response();
		response.setSaleResponseData(sales.getSalesId());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/getSales/{saleId}")
	public ResponseEntity<Response> getSale(@PathVariable("saleId") Integer saleId) throws Exception {

		SalesDTO sales = salesService.getSale(saleId);

		Response response = new Response();
		response.setStatus(Response.SUCCESS_STATUS);
		response.setSaleData(sales);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getAllSalesData")
	public ResponseEntity<Response> getAllSalesDetails() {

		Response response = new Response();
		response.setSaleResponseData(salesService.getAllSalesDetails());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}
	
	@GetMapping("/getAllSalesDataForReceipt")
	public ResponseEntity<Response> getAllSalesDetailsForReceipt() {

		Response response = new Response();
		response.setSaleResponseData(salesService.getAllSalesDetailsForReceipt());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}


	@PostMapping("/deleteSale/{saleId}")
	public ResponseEntity<Response> deleteSale(@PathVariable("saleId") Integer saleId) throws Exception {

		Integer result = salesService.deleteSale(saleId);

		Response response = new Response();
		response.setStatus(result == 0 ? Response.FAIL_STATUS : Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);
	}

}