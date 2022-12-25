package com.sales_inventry.springclient.reotrfit;

import com.sales_inventry.springclient.model.ReceiptDTO;
import com.sales_inventry.springclient.model.ResponseEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReceiptApi {

  @GET("/receipt/getAllReceiptData")
  Call<ResponseEntity> getAllReceiptData();

  @POST("/receipt/saveReceipt")
  Call<ReceiptDTO> saveReceipt(@Body ReceiptDTO receipt);

  @GET("/receipt/getReceipt/{receiptId}")
  Call<ResponseEntity> getReceipt(@Path("receiptId") Integer receiptId);

  @POST("/receipt/deleteReceipt/{receiptId}")
  Call<ResponseEntity> deleteReceipt(@Path("receiptId") Integer receiptId);

}
