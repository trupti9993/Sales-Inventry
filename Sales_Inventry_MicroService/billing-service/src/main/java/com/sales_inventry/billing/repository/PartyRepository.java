package com.sales_inventry.billing.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sales_inventry.billing.entity.Party;

public interface PartyRepository extends JpaRepository<Party, Integer> {

	@Cacheable(value = "partyCache", key = "#partyId", condition = "#partyId!=null")
	Optional<Party> findByPartyId(Integer partyId);

}
