package com.sales_inventry.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales_inventry.app.dto.ProductDTO;
import com.sales_inventry.app.entities.Product;
import com.sales_inventry.app.repository.ProductRepository;
import com.sales_inventry.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<ProductDTO> getAllProductsDetails() {
		List<Product> prodList = (List<Product>) productRepository.findAll();
		List<ProductDTO> prodDtoList = new ArrayList<ProductDTO>();

		for (Product prod : prodList) {
			prodDtoList.add(new ProductDTO(prod));
		}

		return prodDtoList;
	}

	@Override
	public void saveProductToDB(ProductDTO productDto) {
		productRepository.save(new Product(productDto));
	}

	@Override
	public ProductDTO getProduct(Integer productId) throws Exception {

		Optional<Product> product = productRepository.findByProdId(productId);

		if (!product.isPresent()) {
			throw new Exception("No Data Found..!");
		}

		return product.isPresent() ? new ProductDTO(product.get()) : null;
	}

}