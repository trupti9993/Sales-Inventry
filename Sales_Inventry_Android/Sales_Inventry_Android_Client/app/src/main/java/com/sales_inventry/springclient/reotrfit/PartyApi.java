package com.sales_inventry.springclient.reotrfit;

import com.sales_inventry.springclient.model.PartyDTO;
import com.sales_inventry.springclient.model.ResponseEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PartyApi {

  @GET("/party/getAllPartyData")
  Call<ResponseEntity> getAllParty();

  @POST("/party/saveParty")
  Call<PartyDTO> saveParty(@Body PartyDTO party);

  @GET("/party/getParty/{partyId}")
  Call<ResponseEntity> getParty(@Path("partyId") Integer partyId);

  @POST("/party/deleteParty/{partyId}")
  Call<ResponseEntity> deleteParty(@Path("partyId") Integer partyId);

}
