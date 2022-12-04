package com.sales_inventry.app.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.sales_inventry.app.entities.Party;

public interface PartyRepository extends JpaRepository<Party, Integer> {

	@Cacheable(value = "partyCache", key = "#partyId", condition = "#partyId!=null")
	Optional<Party> findByPartyId(Integer partyId);

	@Procedure(procedureName = "deleteParty", outputParameterName = "status")
	Integer deleteParty(@Param("partyId") Integer partyId);

}
