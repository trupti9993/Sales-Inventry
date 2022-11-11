package com.sales_inventry.app.service;

import java.util.List;

import com.sales_inventry.app.dto.PaymentDTO;

public interface PaymentService {

	List<PaymentDTO> getAllPaymentDetails();

	void savePaymentToDB(PaymentDTO payment);

	PaymentDTO getPayment(Integer paymentId) throws Exception;

}
