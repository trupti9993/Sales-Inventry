package com.sales_inventry.app.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sales_inventry.app.entities.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

	@Cacheable(value = "purchaseCache", key = "#purchaseId", condition = "#purchaseId!=null")
	Optional<Purchase> findByPurchaseId(Integer purchaseId);

}
