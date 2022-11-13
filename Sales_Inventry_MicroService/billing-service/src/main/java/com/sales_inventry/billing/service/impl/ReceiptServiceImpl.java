package com.sales_inventry.billing.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales_inventry.billing.dto.ReceiptDTO;
import com.sales_inventry.billing.entity.Receipt;
import com.sales_inventry.billing.repository.EmployeeRepository;
import com.sales_inventry.billing.repository.PartyRepository;
import com.sales_inventry.billing.repository.ReceiptRepository;
import com.sales_inventry.billing.repository.SalesRepository;
import com.sales_inventry.billing.service.ReceiptService;

@Service
public class ReceiptServiceImpl implements ReceiptService {

	@Autowired
	ReceiptRepository receiptRepository;

	@Autowired
	SalesRepository salesRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PartyRepository partyRepository;

	@Override
	public List<ReceiptDTO> getAllReciptsDetails() {
		List<Receipt> partyList = (List<Receipt>) receiptRepository.findAll();
		List<ReceiptDTO> partyDtoList = new ArrayList<ReceiptDTO>();

		for (Receipt emp : partyList) {
			partyDtoList.add(new ReceiptDTO(emp));
		}

		return partyDtoList;
	}

	@Override
	public void saveReceiptToDB(ReceiptDTO receiptDto) {
		Receipt receipt = new Receipt();
		receipt.setAmount(receiptDto.getAmount());
		receipt.setDate(new Date());
		receipt.setEmpId(employeeRepository.findByEmployeeId(receiptDto.getEmpId()).get());
		receipt.setPartyId(partyRepository.findByPartyId(receiptDto.getPartyId()).get());
		receipt.setSalesId(salesRepository.findBySalesId(receiptDto.getSalesId()).get());
		receiptRepository.save(receipt);
	}

	@Override
	public ReceiptDTO getReceipt(Integer receiptId) throws Exception {

		Optional<Receipt> party = receiptRepository.findByReceiptId(receiptId);

		if (!party.isPresent()) {
			throw new Exception("No Data Found..!");
		}

		return party.isPresent() ? new ReceiptDTO(party.get()) : null;
	}

}