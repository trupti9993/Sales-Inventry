package com.sales_inventry.app.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.sales_inventry.app.entities.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

	@Cacheable(value = "purchaseCache", key = "#purchaseId", condition = "#purchaseId!=null")
	Optional<Purchase> findByPurchaseId(Integer purchaseId);

	@Procedure(procedureName = "deletePurchase", outputParameterName = "status")
	Integer deletePurchase(@Param("purchaseId") Integer purchaseId);

}
