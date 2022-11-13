package com.sales_inventry.catlog.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sales_inventry.catlog.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Cacheable(value = "prodCache", key = "#prodId", condition = "#prodId!=null")
	Optional<Product> findByProdId(Integer prodId);

}
