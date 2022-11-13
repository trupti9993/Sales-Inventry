package com.sales_inventry.catlog.service;

import java.util.List;

import com.sales_inventry.catlog.dto.PartyDTO;

public interface PartyService {

	List<PartyDTO> getAllPartyDetails();

	void savePartyToDB(PartyDTO party);

	PartyDTO getParty(Integer partyId) throws Exception;

}
