package com.sales_inventry.catlog.service;

import java.util.List;

import com.sales_inventry.catlog.dto.ProductDTO;

public interface ProductService {

	List<ProductDTO> getAllProductsDetails();

	void saveProductToDB(ProductDTO product);

	ProductDTO getProduct(Integer prodId) throws Exception;

}
