package com.sales_inventry.app.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.sales_inventry.app.entities.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer> {

	@Cacheable(value = "salesCache", key = "#salesId", condition = "#salesId!=null")
	Optional<Sales> findBySalesId(Integer salesId);

	@Procedure(procedureName = "deleteSale", outputParameterName = "status")
	Integer deleteSale(@Param("saleId") Integer saleId);

}
