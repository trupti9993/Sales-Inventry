package com.sales_inventry.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sales_inventry.springclient.adapter.PartyAdapter;
import com.sales_inventry.springclient.adapter.ProductAdapter;
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.PartyDTO;
import com.sales_inventry.springclient.model.ProductDTO;
import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.ProductApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    private static Integer productId=-1;
    public static Integer getProductId() {
        return productId;
    }

    public static void setProductId(Integer prodId) {
        productId=prodId;
    }

  private RecyclerView recyclerView;

    RetrofitService retrofitService = new RetrofitService();
    ProductApi productApi = retrofitService.getRetrofit().create(ProductApi.class);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_employee_list);

    recyclerView = findViewById(R.id.employeeList_recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    FloatingActionButton floatingActionButton = findViewById(R.id.employeeList_fab);
    floatingActionButton.setOnClickListener(view -> {
      Intent intent = new Intent(this, ProductForm.class);
      startActivity(intent);
    });
  }

  private void loadAllProducts() {

      productApi.getAllProductData()
        .enqueue(new Callback<ResponseEntity>() {
          @Override
          public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
           try {
               List<ProductDTO> responseData = (List<ProductDTO>) response.body().getProductResponseData();

               populateListView(responseData);
           }catch (Exception e){
               Toast.makeText(ProductListActivity.this, "Save successful! "+e.toString(), Toast.LENGTH_SHORT).show();

           }
          }

          @Override
          public void onFailure(Call<ResponseEntity> call, Throwable t) {
            Toast.makeText(ProductListActivity.this, "Failed to load employees ", Toast.LENGTH_SHORT).show();
          }
        });
  }


  private void populateListView(List<ProductDTO> productList) {
      ProductAdapter productAdapter = new ProductAdapter(productList,this);
      recyclerView.setAdapter(productAdapter);
  }

  @Override
  protected void onResume() {
    super.onResume();
      loadAllProducts();
  }

    public void deleteProduct(Integer productId) {
        productApi.deleteProduct(productId).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {

                Toast.makeText(ProductListActivity.this, "Delete successful! ", Toast.LENGTH_SHORT).show();

                loadAllProducts();
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {

                Toast.makeText(ProductListActivity.this, "Delete failed!!!", Toast.LENGTH_SHORT).show();
                Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
        });


    }

    public void updateProduct(Integer productId) {
        ProductListActivity.setProductId(productId);
        Intent intent = new Intent(this, ProductForm.class);
        startActivity(intent);
    }
}