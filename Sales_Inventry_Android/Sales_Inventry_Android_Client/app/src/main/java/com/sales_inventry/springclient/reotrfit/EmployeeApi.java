package com.sales_inventry.springclient.reotrfit;

import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.ResponseEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeApi {

  @GET("/employee/getAllEmployeeData")
  Call<ResponseEntity> getAllEmployees();

  @POST("/employee/saveEmployee")
  Call<EmployeeDTO> saveEmployee(@Body EmployeeDTO employee);

  @GET("/employee/getEmployee/{empId}")
  Call<ResponseEntity> getEmployee(@Path("empId") Integer empId);

  @POST("/employee/deleteEmployee/{empId}")
  Call<ResponseEntity> deleteEmployee(@Path("empId") Integer empId);

}
