package com.sales_inventry.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales_inventry.app.dto.PartyDTO;
import com.sales_inventry.app.entities.Party;
import com.sales_inventry.app.repository.PartyRepository;
import com.sales_inventry.app.service.PartyService;

@Service
public class PartyServiceImpl implements PartyService {

	@Autowired
	PartyRepository partyRepository;

	@Override
	public List<PartyDTO> getAllPartyDetails() {
		List<Party> partyList = (List<Party>) partyRepository.findAll();
		List<PartyDTO> partyDtoList = new ArrayList<PartyDTO>();

		for (Party party : partyList) {
			partyDtoList.add(new PartyDTO(party));
		}

		return partyDtoList;
	}

	@Override
	public void savePartyToDB(PartyDTO partyDto) {
		partyRepository.save(new Party(partyDto));
	}

	@Override
	public PartyDTO getParty(Integer partyId) throws Exception {

		Optional<Party> party = partyRepository.findByPartyId(partyId);

		if (!party.isPresent()) {
			throw new Exception("No Data Found..!");
		}

		return party.isPresent() ? new PartyDTO(party.get()) : null;
	}

	@Override
	public Integer deleteParty(Integer partyId) throws Exception {
		return partyRepository.deleteParty(partyId);
	}

}