package com.sales_inventry.app.service;

import java.util.List;

import com.sales_inventry.app.dto.SalesDTO;

public interface SalesService {

	List<SalesDTO> getAllSalesDetails();

	void saveSalesToDB(SalesDTO sale);

	SalesDTO getSale(Integer saleId) throws Exception;
	
	Integer deleteSale(Integer saleId) throws Exception;

}
