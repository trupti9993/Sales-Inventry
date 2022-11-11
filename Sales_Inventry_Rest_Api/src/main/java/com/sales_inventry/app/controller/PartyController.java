package com.sales_inventry.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales_inventry.app.dto.PartyDTO;
import com.sales_inventry.app.dto.Response;
import com.sales_inventry.app.service.PartyService;

@RestController
@RequestMapping("/party")
public class PartyController {

	@Autowired
	PartyService partyService;

	@PostMapping(value = "/saveParty")
	public ResponseEntity<Response> saveEmployee(@RequestBody PartyDTO party) {

		partyService.savePartyToDB(party);

		Response response = new Response();
		response.setResponseData(party.getPartyId());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/getParty/{partyId}")
	public ResponseEntity<Response> saveEmployeesToDB(@PathVariable("partyId") Integer partyId) throws Exception {

		PartyDTO party = partyService.getParty(partyId);

		Response response = new Response();
		response.setStatus(Response.SUCCESS_STATUS);
		response.setResponseData(party);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getAllPartyData")
	public ResponseEntity<Response> getAllPartyDetails() {

		Response response = new Response();
		response.setResponseData(partyService.getAllPartyDetails());
		response.setStatus(Response.SUCCESS_STATUS);

		return ResponseEntity.ok(response);

	}

}