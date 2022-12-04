package com.sales_inventry.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales_inventry.app.dto.ProductDTO;
import com.sales_inventry.app.dto.Response;
import com.sales_inventry.app.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping(value = "/saveProduct")
	public ResponseEntity<Response> saveEmployee(@RequestBody ProductDTO product) {

		productService.saveProductToDB(product);

		Response response = new Response();
		response.setProductResponseData(product.getProdId());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/getProduct/{productId}")
	public ResponseEntity<Response> getProduct(@PathVariable("productId") Integer productId) throws Exception {

		ProductDTO product = productService.getProduct(productId);

		Response response = new Response();
		response.setStatus(Response.SUCCESS_STATUS);
		response.setProductData(product);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getAllProductData")
	public ResponseEntity<Response> getAllProductDetails() {

		Response response = new Response();
		response.setProductResponseData(productService.getAllProductsDetails());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

}