package com.sales_inventry.billing.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sales_inventry.billing.dto.PartyDTO;

@Entity
public class Party {

	@Id
	@Column(name = "party_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer partyId;

	@Column(name = "party_name", nullable = false)
	String partyName;

	@Column(name = "mobile_no", nullable = false)
	String mobileNo;

	@Column(name = "email", nullable = false)
	String email;

	@Column(name = "address", nullable = false)
	String address;

	@JsonManagedReference
	@OneToMany(mappedBy = "partyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Purchase> purchase;

	@JsonManagedReference
	@OneToMany(mappedBy = "partyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Sales> sale;

	@JsonManagedReference
	@OneToMany(mappedBy = "partyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Receipt> receipts;

	@JsonManagedReference
	@OneToMany(mappedBy = "partyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Payment> payments;

	public Party(PartyDTO partyDto) {
		this.partyId = partyDto.getPartyId();
		this.partyName = partyDto.getPartyName();
		this.mobileNo = partyDto.getMobileNo();
		this.email = partyDto.getEmail();
		this.address = partyDto.getAddress();
	}

	public Party() {

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

	public void setPartyName(String partyName) {
		this.partyName = partyName;
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
