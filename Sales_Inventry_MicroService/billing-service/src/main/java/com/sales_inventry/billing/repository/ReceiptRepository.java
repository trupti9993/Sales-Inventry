package com.sales_inventry.billing.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sales_inventry.billing.entity.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

	@Cacheable(value = "receiptCache", key = "#receiptId", condition = "#receiptId!=null")
	Optional<Receipt> findByReceiptId(Integer receiptId);

}
