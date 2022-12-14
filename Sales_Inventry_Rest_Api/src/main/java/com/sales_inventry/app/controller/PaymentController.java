package com.sales_inventry.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales_inventry.app.dto.PaymentDTO;
import com.sales_inventry.app.dto.Response;
import com.sales_inventry.app.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@PostMapping(value = "/savePayment")
	public ResponseEntity<Response> savePayment(@RequestBody PaymentDTO payment) {

		paymentService.savePaymentToDB(payment);

		Response response = new Response();
		response.setPaymentResponseData(payment.getPaymentId());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/getPayment/{paymentId}")
	public ResponseEntity<Response> getPayment(@PathVariable("paymentId") Integer paymentId) throws Exception {

		PaymentDTO payment = paymentService.getPayment(paymentId);

		Response response = new Response();
		response.setStatus(Response.SUCCESS_STATUS);
		response.setPaymentData(payment);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getAllPaymentData")
	public ResponseEntity<Response> getAllPaymentDetails() {

		Response response = new Response();
		response.setPaymentResponseData(paymentService.getAllPaymentDetails());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@PostMapping("/deletePayment/{paymentId}")
	public ResponseEntity<Response> deletePayment(@PathVariable("paymentId") Integer paymentId) throws Exception {

		Integer result = paymentService.deletePayment(paymentId);

		Response response = new Response();
		response.setStatus(result == 0 ? Response.FAIL_STATUS : Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);
	}

}