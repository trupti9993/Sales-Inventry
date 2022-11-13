package com.sales_inventry.stock.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sales_inventry.stock.entity.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer> {

	@Cacheable(value = "salesCache", key = "#salesId", condition = "#salesId!=null")
	Optional<Sales> findBySalesId(Integer salesId);

}
