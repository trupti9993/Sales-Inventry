package com.sales_inventry.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sales_inventry.springclient.adapter.EmployeeAdapter;
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListActivity extends AppCompatActivity {

    private static Integer employeeId=-1;
    public static Integer getEmployeeId() {
        return employeeId;
    }

    public static void setEmployeeId(Integer empId) {
         employeeId=empId;
    }

  private RecyclerView recyclerView;

    RetrofitService retrofitService = new RetrofitService();
    EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_employee_list);

    recyclerView = findViewById(R.id.employeeList_recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    FloatingActionButton floatingActionButton = findViewById(R.id.employeeList_fab);
    floatingActionButton.setOnClickListener(view -> {
      Intent intent = new Intent(this, EmployeeForm.class);
      startActivity(intent);
    });
  }

  private void loadEmployees() {

    employeeApi.getAllEmployees()
        .enqueue(new Callback<ResponseEntity>() {
          @Override
          public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
           try {
               List<EmployeeDTO> responseData = (List<EmployeeDTO>) response.body().getEmpResponseData();

               populateListView(responseData);
           }catch (Exception e){
               Toast.makeText(EmployeeListActivity.this, "Save successful! "+e.toString(), Toast.LENGTH_SHORT).show();

           }
          }

          @Override
          public void onFailure(Call<ResponseEntity> call, Throwable t) {
            Toast.makeText(EmployeeListActivity.this, "Failed to load employees ", Toast.LENGTH_SHORT).show();
          }
        });
  }


  private void populateListView(List<EmployeeDTO> employeeList) {
      EmployeeAdapter employeeAdapter = new EmployeeAdapter(employeeList,this);
      recyclerView.setAdapter(employeeAdapter);
  }

  @Override
  protected void onResume() {
    super.onResume();
    loadEmployees();
  }

    public void deleteEmployee(Integer employeeId) {
        employeeApi.deleteEmployee(employeeId).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {

                Toast.makeText(EmployeeListActivity.this, "Delete successful! ", Toast.LENGTH_SHORT).show();

                loadEmployees();
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {

                Toast.makeText(EmployeeListActivity.this, "Delete failed!!!", Toast.LENGTH_SHORT).show();
                Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
        });


    }

    public void updateEmployee(Integer employeeId) {
        EmployeeListActivity.setEmployeeId(employeeId);
        Intent intent = new Intent(this, EmployeeForm.class);
        startActivity(intent);
    }
}