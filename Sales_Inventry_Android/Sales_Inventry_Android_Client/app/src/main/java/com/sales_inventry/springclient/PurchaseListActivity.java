package com.sales_inventry.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sales_inventry.springclient.adapter.PurchaseAdapter;
import com.sales_inventry.springclient.model.PurchaseDTO;
import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.reotrfit.PurchaseApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseListActivity extends AppCompatActivity {

    private static Integer purchaseId=-1;
    public static Integer getPurchaseId() {
        return purchaseId;
    }

    public static void setPurchaseId(Integer purId) {
        purchaseId=purId;
    }

    private RecyclerView recyclerView;

    RetrofitService retrofitService = new RetrofitService();
    PurchaseApi purchaseApi = retrofitService.getRetrofit().create(PurchaseApi.class);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_purchase_list);

    recyclerView = findViewById(R.id.employeeList_recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    FloatingActionButton floatingActionButton = findViewById(R.id.employeeList_fab);
    floatingActionButton.setOnClickListener(view -> {
      Intent intent = new Intent(this, PurchaseForm.class);
      startActivity(intent);
    });
  }

  private void loadPurchaseData() {

      purchaseApi.getAllPurchaseData()
        .enqueue(new Callback<ResponseEntity>() {
          @Override
          public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
           try {
               List<PurchaseDTO> responseData = (List<PurchaseDTO>) response.body().getPurchaseResponseData();

               populateListView(responseData);
           }catch (Exception e){
               Toast.makeText(PurchaseListActivity.this, "Failed to load Purchase data..! ", Toast.LENGTH_SHORT).show();

           }
          }

          @Override
          public void onFailure(Call<ResponseEntity> call, Throwable t) {
            Toast.makeText(PurchaseListActivity.this, "Failed to load Purchase data..! ", Toast.LENGTH_SHORT).show();
          }
        });
  }


  private void populateListView(List<PurchaseDTO> purchaseList) {
      PurchaseAdapter purchaseAdapter = new PurchaseAdapter(purchaseList,this);
      recyclerView.setAdapter(purchaseAdapter);
  }

  @Override
  protected void onResume() {
    super.onResume();
      loadPurchaseData();
  }

    public void deletePurchase(Integer purchaseId) {

          purchaseApi.deletePurchase(purchaseId).enqueue(new Callback<ResponseEntity>() {
              @Override
              public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {

                  Toast.makeText(PurchaseListActivity.this, "Purchase Delete successful..! ", Toast.LENGTH_SHORT).show();

                  loadPurchaseData();
              }

              @Override
              public void onFailure(Call<ResponseEntity> call, Throwable t) {

                  Toast.makeText(PurchaseListActivity.this, "Purchase Delete failed..!", Toast.LENGTH_SHORT).show();
                  Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
              }
          });


    }

    public void updatePurchase(Integer purchaseId) {
        PurchaseListActivity.setPurchaseId(purchaseId);
        Intent intent = new Intent(this, PurchaseForm.class);
        startActivity(intent);
    }
}