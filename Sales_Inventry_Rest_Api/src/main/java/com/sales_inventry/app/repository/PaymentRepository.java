package com.sales_inventry.app.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sales_inventry.app.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	@Cacheable(value = "paymentCache", key = "#paymentId", condition = "#paymentId!=null")
	Optional<Payment> findByPaymentId(Integer paymentId);

}
