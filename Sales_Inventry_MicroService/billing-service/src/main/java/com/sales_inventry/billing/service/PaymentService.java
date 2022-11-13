package com.sales_inventry.billing.service;

import java.util.List;

import com.sales_inventry.billing.dto.PaymentDTO;

public interface PaymentService {

	List<PaymentDTO> getAllPaymentDetails();

	void savePaymentToDB(PaymentDTO payment);

	PaymentDTO getPayment(Integer paymentId) throws Exception;

}
