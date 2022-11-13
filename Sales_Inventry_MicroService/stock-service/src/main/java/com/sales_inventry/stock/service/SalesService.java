package com.sales_inventry.stock.service;

import java.util.List;

import com.sales_inventry.stock.dto.SalesDTO;

public interface SalesService {

	List<SalesDTO> getAllSalesDetails();

	void saveSalesToDB(SalesDTO sale);

	SalesDTO getSale(Integer saleId) throws Exception;

}
