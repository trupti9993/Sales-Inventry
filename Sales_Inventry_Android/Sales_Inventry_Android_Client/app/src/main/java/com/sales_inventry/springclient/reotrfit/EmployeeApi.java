package com.sales_inventry.springclient.reotrfit;

import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.ResponseEntity;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EmployeeApi {

  @GET("/employee/getAllEmployeeData")
  Call<ResponseEntity> getAllEmployees();

  @POST("/employee/saveEmployee")
  Call<EmployeeDTO> save(@Body EmployeeDTO employee);
}
