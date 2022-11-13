package com.sales_inventry.stock.dto;

import com.sales_inventry.stock.entity.Party;

public class PartyDTO {

	Integer partyId;

	String partyName;

	String mobileNo;

	String email;

	String address;

	public PartyDTO(Party party) {
		this.partyId = party.getPartyId();
		this.partyName = party.getPartyName();
		this.mobileNo = party.getMobileNo();
		this.email = party.getEmail();
		this.address = party.getAddress();
	}

	public PartyDTO() {
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setFirstName(String firstName) {
		this.partyName = firstName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
