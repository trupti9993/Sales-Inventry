package com.sales_inventry.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sales_inventry.springclient.adapter.SaleAdapter;
import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.model.SalesDTO;
import com.sales_inventry.springclient.reotrfit.RetrofitService;
import com.sales_inventry.springclient.reotrfit.SaleApi;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleListActivity extends AppCompatActivity {

    private static Integer saleId=-1;
    public static Integer getSaleId() {
        return saleId;
    }

    public static void setSaleId(Integer id) {
        saleId=id;
    }

    private RecyclerView recyclerView;

    RetrofitService retrofitService = new RetrofitService();
    SaleApi saleApi = retrofitService.getRetrofit().create(SaleApi.class);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sale_list);

    recyclerView = findViewById(R.id.employeeList_recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    FloatingActionButton floatingActionButton = findViewById(R.id.employeeList_fab);
    floatingActionButton.setOnClickListener(view -> {
      Intent intent = new Intent(this, SaleForm.class);
      startActivity(intent);
    });
  }

  private void loadAllSalesData() {

      saleApi.getAllSalesData()
        .enqueue(new Callback<ResponseEntity>() {
          @Override
          public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
           try {
               List<SalesDTO> responseData = (List<SalesDTO>) response.body().getSaleResponseData();

               populateListView(responseData);
           }catch (Exception e){
               Toast.makeText(SaleListActivity.this, "Failed to load Sale data..! "+ e, Toast.LENGTH_SHORT).show();

           }
          }

          @Override
          public void onFailure(Call<ResponseEntity> call, Throwable t) {
            Toast.makeText(SaleListActivity.this, "Failed to load Sale data..! ", Toast.LENGTH_SHORT).show();
          }
        });
  }


  private void populateListView(List<SalesDTO> salesList) {
      SaleAdapter saleAdapter = new SaleAdapter(salesList,this);
      recyclerView.setAdapter(saleAdapter);
  }

  @Override
  protected void onResume() {
    super.onResume();
      loadAllSalesData();
  }

    public void deleteSale(Integer saleId) {
        saleApi.deleteSale(saleId).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {

                Toast.makeText(SaleListActivity.this, "Sale Delete successful..! ", Toast.LENGTH_SHORT).show();

                loadAllSalesData();
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {

                Toast.makeText(SaleListActivity.this, "Sale Delete failed..!", Toast.LENGTH_SHORT).show();
                Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
        });


    }

    public void updateSale(Integer saleId) {
        SaleListActivity.setSaleId(saleId);
        Intent intent = new Intent(this, SaleForm.class);
        startActivity(intent);
    }
}