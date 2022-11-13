package com.sales_inventry.stock.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales_inventry.stock.dto.SalesDTO;
import com.sales_inventry.stock.entity.Sales;
import com.sales_inventry.stock.repository.EmployeeRepository;
import com.sales_inventry.stock.repository.PartyRepository;
import com.sales_inventry.stock.repository.ProductRepository;
import com.sales_inventry.stock.repository.SalesRepository;
import com.sales_inventry.stock.service.SalesService;

@Service
public class SalesServiceImpl implements SalesService {

	@Autowired
	SalesRepository salesRepository;

	@Autowired
	ProductRepository prodRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PartyRepository partyRepository;

	@Override
	public List<SalesDTO> getAllSalesDetails() {
		List<Sales> partyList = (List<Sales>) salesRepository.findAll();
		List<SalesDTO> partyDtoList = new ArrayList<SalesDTO>();

		for (Sales emp : partyList) {
			partyDtoList.add(new SalesDTO(emp));
		}

		return partyDtoList;
	}

	@Override
	public void saveSalesToDB(SalesDTO salesDTO) {
		Sales sales = new Sales();

		sales.setDate(new Date());
		sales.setEmpId(employeeRepository.findByEmployeeId(salesDTO.getEmpId()).get());
		sales.setPartyId(partyRepository.findByPartyId(salesDTO.getPartyId()).get());
		sales.setProdId(prodRepository.findByProdId(salesDTO.getProdId()).get());
		sales.setQuantity(salesDTO.getQuantity());
		sales.setRate(salesDTO.getRate());
		sales.setAmount(salesDTO.getAmount());
		sales.setDiscount(salesDTO.getDiscount());
		sales.setTax(salesDTO.getTax());
		sales.setNetAmount(salesDTO.getNetAmount());

		salesRepository.save(sales);
	}

	@Override
	public SalesDTO getSale(Integer SalesId) throws Exception {

		Optional<Sales> record = salesRepository.findBySalesId(SalesId);

		if (!record.isPresent()) {
			throw new Exception("No Data Found..!");
		}

		return record.isPresent() ? new SalesDTO(record.get()) : null;
	}

}