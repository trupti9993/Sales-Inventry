package com.sales_inventry.billing.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales_inventry.billing.dto.PaymentDTO;
import com.sales_inventry.billing.entity.Payment;
import com.sales_inventry.billing.repository.EmployeeRepository;
import com.sales_inventry.billing.repository.PartyRepository;
import com.sales_inventry.billing.repository.PaymentRepository;
import com.sales_inventry.billing.repository.PurchaseRepository;
import com.sales_inventry.billing.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	PurchaseRepository purchaseRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PartyRepository partyRepository;

	@Override
	public List<PaymentDTO> getAllPaymentDetails() {
		List<Payment> partyList = (List<Payment>) paymentRepository.findAll();
		List<PaymentDTO> partyDtoList = new ArrayList<PaymentDTO>();

		for (Payment emp : partyList) {
			partyDtoList.add(new PaymentDTO(emp));
		}

		return partyDtoList;
	}

	@Override
	public void savePaymentToDB(PaymentDTO paymentDto) {
		Payment payment = new Payment();
		payment.setAmount(paymentDto.getAmount());
		payment.setDate(new Date());
		payment.setEmpId(employeeRepository.findByEmployeeId(paymentDto.getEmpId()).get());
		payment.setPartyId(partyRepository.findByPartyId(paymentDto.getPartyId()).get());
		payment.setPurchaseId(purchaseRepository.findByPurchaseId(paymentDto.getPurchaseId()).get());
		paymentRepository.save(payment);
	}

	@Override
	public PaymentDTO getPayment(Integer paymentId) throws Exception {

		Optional<Payment> party = paymentRepository.findByPaymentId(paymentId);

		if (!party.isPresent()) {
			throw new Exception("No Data Found..!");
		}

		return party.isPresent() ? new PaymentDTO(party.get()) : null;
	}

}