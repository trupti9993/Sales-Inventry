package com.sales_inventry.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sales_inventry.springclient.adapter.PaymentAdapter;
import com.sales_inventry.springclient.model.PaymentDTO;
import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.reotrfit.PaymentApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentListActivity extends AppCompatActivity {

    private static Integer paymentId=-1;
    public static Integer getPaymentId() {
        return paymentId;
    }

    public static void setPaymentId(Integer payId) {
        paymentId=payId;
    }

  private RecyclerView recyclerView;

    RetrofitService retrofitService = new RetrofitService();
    PaymentApi paymentApi = retrofitService.getRetrofit().create(PaymentApi.class);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_payment_list);

    recyclerView = findViewById(R.id.employeeList_recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    FloatingActionButton floatingActionButton = findViewById(R.id.employeeList_fab);
    floatingActionButton.setOnClickListener(view -> {
      Intent intent = new Intent(this, PaymentForm.class);
      startActivity(intent);
    });
  }

  private void loadEmployees() {

      paymentApi.getAllPayment()
        .enqueue(new Callback<ResponseEntity>() {
          @Override
          public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
           try {
               List<PaymentDTO> responseData = (List<PaymentDTO>) response.body().getPaymentResponseData();

               populateListView(responseData);
           }catch (Exception e){
               Toast.makeText(PaymentListActivity.this, "Failed to load Payment data..!"+ e, Toast.LENGTH_SHORT).show();

           }
          }

          @Override
          public void onFailure(Call<ResponseEntity> call, Throwable t) {
            Toast.makeText(PaymentListActivity.this, "Failed to load Payment data..! ", Toast.LENGTH_SHORT).show();
          }
        });
  }


  private void populateListView(List<PaymentDTO> paymentList) {
     PaymentAdapter paymentAdapter = new PaymentAdapter(paymentList,this);
      //recyclerView.setAdapter(paymentAdapter);
  }

  @Override
  protected void onResume() {
    super.onResume();
    loadEmployees();
  }

    public void deletePayment(Integer paymentId) {
        paymentApi.deletePayment(paymentId).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {

                Toast.makeText(PaymentListActivity.this, "Payment Delete successful..! ", Toast.LENGTH_SHORT).show();

                loadEmployees();
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {

                Toast.makeText(PaymentListActivity.this, "Payment Deletion failed..!", Toast.LENGTH_SHORT).show();
                Logger.getLogger(PaymentForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
        });


    }

    public void updatePayment(Integer paymentId) {
        PaymentListActivity.setPaymentId(paymentId);
        Intent intent = new Intent(this, PaymentForm.class);
        startActivity(intent);
    }
}