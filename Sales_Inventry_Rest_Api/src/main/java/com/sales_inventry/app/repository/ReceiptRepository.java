package com.sales_inventry.app.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.sales_inventry.app.entities.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

	@Cacheable(value = "receiptCache", key = "#receiptId", condition = "#receiptId!=null")
	Optional<Receipt> findByReceiptId(Integer receiptId);

	@Procedure(procedureName = "deleteReceipt", outputParameterName = "status")
	Integer deleteReceipt(@Param("receiptId") Integer receiptId);

}
