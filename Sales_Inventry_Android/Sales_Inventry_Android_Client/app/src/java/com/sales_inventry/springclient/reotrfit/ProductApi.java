package com.sales_inventry.springclient.reotrfit;

import com.sales_inventry.springclient.model.ProductDTO;
import com.sales_inventry.springclient.model.ResponseEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductApi {

  @GET("/product/getAllProductData")
  Call<ResponseEntity> getAllProductData();

  @POST("/product/saveProduct")
  Call<ProductDTO> saveProduct(@Body ProductDTO product);

  @GET("/product/getProduct/{productId}")
  Call<ResponseEntity> getProduct(@Path("productId") Integer productId);

  @POST("/product/deleteProduct/{productId}")
  Call<ResponseEntity> deleteProduct(@Path("productId") Integer productId);
}
