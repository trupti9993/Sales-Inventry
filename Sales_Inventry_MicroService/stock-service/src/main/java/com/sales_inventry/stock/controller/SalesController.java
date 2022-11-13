package com.sales_inventry.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales_inventry.stock.dto.Response;
import com.sales_inventry.stock.dto.SalesDTO;
import com.sales_inventry.stock.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {

	@Autowired
	SalesService salesService;

	@PostMapping(value = "/saveSales")
	public ResponseEntity<Response> saveEmployee(@RequestBody SalesDTO sales) {

		salesService.saveSalesToDB(sales);

		Response response = new Response();
		response.setResponseData(sales.getSalesId());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/getSales/{saleId}")
	public ResponseEntity<Response> saveEmployeesToDB(@PathVariable("saleId") Integer saleId) throws Exception {

		SalesDTO sales = salesService.getSale(saleId);

		Response response = new Response();
		response.setStatus(Response.SUCCESS_STATUS);
		response.setResponseData(sales);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getAllSalesData")
	public ResponseEntity<Response> getAllSalesDetails() {

		Response response = new Response();
		response.setResponseData(salesService.getAllSalesDetails());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

}