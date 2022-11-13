package com.sales_inventry.stock.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales_inventry.stock.dto.PurchaseDTO;
import com.sales_inventry.stock.entity.Purchase;
import com.sales_inventry.stock.repository.EmployeeRepository;
import com.sales_inventry.stock.repository.PartyRepository;
import com.sales_inventry.stock.repository.ProductRepository;
import com.sales_inventry.stock.repository.PurchaseRepository;
import com.sales_inventry.stock.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	PurchaseRepository purchaseRepository;

	@Autowired
	ProductRepository prodRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PartyRepository partyRepository;

	@Override
	public List<PurchaseDTO> getAllPurchaseDetails() {
		List<Purchase> partyList = (List<Purchase>) purchaseRepository.findAll();
		List<PurchaseDTO> partyDtoList = new ArrayList<PurchaseDTO>();

		for (Purchase emp : partyList) {
			partyDtoList.add(new PurchaseDTO(emp));
		}

		return partyDtoList;
	}

	@Override
	public void savePurchaseToDB(PurchaseDTO purchaseDTO) {
		Purchase purchase = new Purchase();

		purchase.setDate(new Date());
		purchase.setEmpId(employeeRepository.findByEmployeeId(purchaseDTO.getEmpId()).get());
		purchase.setPartyId(partyRepository.findByPartyId(purchaseDTO.getPartyId()).get());
		purchase.setProdId(prodRepository.findByProdId(purchaseDTO.getProdId()).get());
		purchase.setQuantity(purchaseDTO.getQuantity());
		purchase.setRate(purchaseDTO.getRate());
		purchase.setAmount(purchaseDTO.getAmount());
		purchase.setDiscount(purchaseDTO.getDiscount());
		purchase.setTax(purchaseDTO.getTax());
		purchase.setNetAmount(purchaseDTO.getNetAmount());

		purchaseRepository.save(purchase);
	}

	@Override
	public PurchaseDTO getPurchase(Integer PurchaseId) throws Exception {

		Optional<Purchase> record = purchaseRepository.findByPurchaseId(PurchaseId);

		if (!record.isPresent()) {
			throw new Exception("No Data Found..!");
		}

		return record.isPresent() ? new PurchaseDTO(record.get()) : null;
	}

}