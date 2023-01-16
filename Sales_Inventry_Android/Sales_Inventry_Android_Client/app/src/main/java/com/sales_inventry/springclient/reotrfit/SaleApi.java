package com.sales_inventry.springclient.reotrfit;

import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.model.SalesDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SaleApi {

  @GET("/sales/getAllSalesDataForReceipt")
  Call<ResponseEntity> getAllSalesDataForReceipt();

  @GET("/sales/getAllSalesData")
  Call<ResponseEntity> getAllSalesData();

  @POST("/sales/saveSales")
  Call<SalesDTO> saveSales(@Body SalesDTO sale);

  @GET("/sales/getSales/{saleId}")
  Call<ResponseEntity> getSales(@Path("saleId") Integer saleId);

  @POST("/sales/deleteSale/{saleId}")
  Call<ResponseEntity> deleteSale(@Path("saleId") Integer saleId);


}
