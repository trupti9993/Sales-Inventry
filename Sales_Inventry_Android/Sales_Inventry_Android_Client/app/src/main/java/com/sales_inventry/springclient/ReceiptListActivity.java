package com.sales_inventry.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sales_inventry.springclient.adapter.PartyAdapter;
import com.sales_inventry.springclient.adapter.ReceiptAdapter;
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.PartyDTO;
import com.sales_inventry.springclient.model.ReceiptDTO;
import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.ReceiptApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiptListActivity extends AppCompatActivity {

    private static Integer receiptId=-1;
    public static Integer getReceiptId() {
        return receiptId;
    }

    public static void setReceiptId(Integer id) {
        receiptId=id;
    }

  private RecyclerView recyclerView;

    RetrofitService retrofitService = new RetrofitService();
    ReceiptApi receiptApi = retrofitService.getRetrofit().create(ReceiptApi.class);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_employee_list);

    recyclerView = findViewById(R.id.employeeList_recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    FloatingActionButton floatingActionButton = findViewById(R.id.employeeList_fab);
    floatingActionButton.setOnClickListener(view -> {
      Intent intent = new Intent(this, ReceiptForm.class);
      startActivity(intent);
    });
  }

  private void loadReceiptData() {

      receiptApi.getAllReceiptData()
        .enqueue(new Callback<ResponseEntity>() {
          @Override
          public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
           try {
               List<ReceiptDTO> responseData = (List<ReceiptDTO>) response.body().getReceiptResponseData();

               populateListView(responseData);
           }catch (Exception e){
               Toast.makeText(ReceiptListActivity.this, "Save successful! "+e.toString(), Toast.LENGTH_SHORT).show();

           }
          }

          @Override
          public void onFailure(Call<ResponseEntity> call, Throwable t) {
            Toast.makeText(ReceiptListActivity.this, "Failed to load employees ", Toast.LENGTH_SHORT).show();
          }
        });
  }


  private void populateListView(List<ReceiptDTO> employeeList) {
      ReceiptAdapter receiptAdapter = new ReceiptAdapter(employeeList,this);
      recyclerView.setAdapter(receiptAdapter);
  }

  @Override
  protected void onResume() {
    super.onResume();
      loadReceiptData();
  }

    public void deleteReceipt(Integer receiptId) {
        receiptApi.deleteReceipt(receiptId).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {

                Toast.makeText(ReceiptListActivity.this, "Delete successful! ", Toast.LENGTH_SHORT).show();

                loadReceiptData();
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {

                Toast.makeText(ReceiptListActivity.this, "Delete failed!!!", Toast.LENGTH_SHORT).show();
                Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
        });


    }

    public void updateReceipt(Integer receiptId) {
        ReceiptListActivity.setReceiptId(receiptId);
        Intent intent = new Intent(this, EmployeeForm.class);
        startActivity(intent);
    }
}