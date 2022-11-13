package com.sales_inventry.api_gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

	@GetMapping("/CatlogServiceFallBack")
	public String catlogServiceFallBackMethod() {
		return "Catlog service is not running";
	}

	@GetMapping("/ProductServiceFallBack")
	public String productServiceFallBackMethod() {
		return "Product service is not running";
	}

	@GetMapping("/BillingServiceFallBack")
	public String billingServiceFallBackMethod() {
		return "Billing service is not running";
	}

}
