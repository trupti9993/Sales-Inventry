package com.sales_inventry.springclient.reotrfit;
import com.sales_inventry.springclient.model.PaymentDTO;
import com.sales_inventry.springclient.model.ResponseEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PaymentApi {

  @GET("/payment/getAllPaymentData")
  Call<ResponseEntity> getAllPayment();

  @POST("/payment/savePayment")
  Call<PaymentDTO> savePayment(@Body PaymentDTO employee);

  @GET("/payment/getPayment/{paymentId}")
  Call<ResponseEntity> getPayment(@Path("paymentId") Integer paymentId);

  @POST("/payment/deletePayment/{paymentId}")
  Call<ResponseEntity> deletePayment(@Path("paymentId") Integer paymentId);
}
