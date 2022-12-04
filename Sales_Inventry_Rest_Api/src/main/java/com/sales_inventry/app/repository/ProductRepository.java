package com.sales_inventry.app.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.sales_inventry.app.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Cacheable(value = "prodCache", key = "#prodId", condition = "#prodId!=null")
	Optional<Product> findByProdId(Integer prodId);

	@Procedure(procedureName = "deleteProduct", outputParameterName = "status")
	Integer deleteProduct(@Param("productId") Integer productId);

}
