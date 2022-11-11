package com.sales_inventry.app.service;

import java.util.List;

import com.sales_inventry.app.dto.PartyDTO;

public interface PartyService {

	List<PartyDTO> getAllPartyDetails();

	void savePartyToDB(PartyDTO party);

	PartyDTO getParty(Integer partyId) throws Exception;

}
