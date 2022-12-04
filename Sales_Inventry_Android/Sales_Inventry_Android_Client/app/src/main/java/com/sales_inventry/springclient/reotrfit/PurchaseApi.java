package com.sales_inventry.springclient.reotrfit;

import com.sales_inventry.springclient.model.PurchaseDTO;
import com.sales_inventry.springclient.model.ResponseEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PurchaseApi {

  @GET("/purchase/getAllPurchaseData")
  Call<ResponseEntity> getAllPurchaseData();

  @POST("/purchase/savePurchase")
  Call<PurchaseDTO> savePurchase(@Body PurchaseDTO purchase);

  @GET("/purchase/getPurchase/{purchaseId}")
  Call<ResponseEntity> getPurchase(@Path("purchaseId") Integer purchaseId);

  @POST("/purchase/deletePurchase/{purchaseId")
  Call<ResponseEntity> deletePurchase(@Path("purchaseId") Integer purchaseId);
}
