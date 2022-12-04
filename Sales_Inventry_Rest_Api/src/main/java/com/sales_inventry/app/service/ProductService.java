package com.sales_inventry.app.service;

import java.util.List;

import com.sales_inventry.app.dto.ProductDTO;

public interface ProductService {

	List<ProductDTO> getAllProductsDetails();

	void saveProductToDB(ProductDTO product);

	ProductDTO getProduct(Integer prodId) throws Exception;

	Integer deleteProduct(Integer productId) throws Exception;

}
