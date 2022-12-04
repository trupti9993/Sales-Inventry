package com.sales_inventry.springclient;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.EmployeeResponseEntity;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeForm extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.create_employee);

    initializeComponents();
  }

  private void initializeComponents() {
    TextInputEditText inputEditTextName = findViewById(R.id.form_textFieldName);
    TextInputEditText inputEditTextId = findViewById(R.id.form_textFieldId);
    TextInputEditText inputEditMobileNo = findViewById(R.id.form_textFieldMobileNo);
    TextInputEditText inputEditEmail = findViewById(R.id.form_textFieldEmail);
    TextInputEditText inputEditAddress = findViewById(R.id.form_textFieldAddress);
    TextInputEditText inputEditPass = findViewById(R.id.form_textFieldPassword);
    MaterialButton buttonSave = findViewById(R.id.form_buttonSave);

     inputEditTextId.setVisibility(View.INVISIBLE);

      RetrofitService retrofitService = new RetrofitService();
      EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);

   if(EmployeeListActivity.getEmployeeId()!=-1){
       employeeApi.getEmployee(EmployeeListActivity.getEmployeeId())
               .enqueue(new Callback<EmployeeResponseEntity>() {
                   @Override
                   public void onResponse(Call<EmployeeResponseEntity> call, Response<EmployeeResponseEntity> response) {
                       EmployeeDTO employeeDTO= (EmployeeDTO) response.body().getEmpData();

                       inputEditTextName.setText(employeeDTO.getEmpName());
                       inputEditMobileNo.setText(employeeDTO.getMobileNo());
                       inputEditEmail.setText(employeeDTO.getEmail());
                       inputEditAddress.setText(employeeDTO.getAddress());
                       inputEditPass.setText(employeeDTO.getPassword());
                       inputEditTextId.setText(String.valueOf( employeeDTO.getEmployeeId()));
                   }

                   @Override
                   public void onFailure(Call<EmployeeResponseEntity> call, Throwable t) {
                       Toast.makeText(EmployeeForm.this, "Employee Fetch failed!!!", Toast.LENGTH_SHORT).show();
                       Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
                   }
               });
       EmployeeListActivity.setEmployeeId(-1);
    }else {
       inputEditTextName.setText("");
       inputEditMobileNo.setText("");
       inputEditEmail.setText("");
       inputEditAddress.setText("");
       inputEditPass.setText("");
       inputEditTextId.setText("-1");
   }




    buttonSave.setOnClickListener(view -> {
      String name = String.valueOf(inputEditTextName.getText());
      String address = String.valueOf(inputEditAddress.getText());
      String email = String.valueOf(inputEditEmail.getText());
      String mobile = String.valueOf(inputEditMobileNo.getText());
      String pass = String.valueOf(inputEditPass.getText());
      Integer employeeId=Integer.parseInt(String.valueOf(inputEditTextId.getText()));

      EmployeeDTO employee = new EmployeeDTO();
      employee.setEmpName(name);
        employee.setEmployeeId(employeeId);
      employee.setAddress(address);
      employee.setEmail(email);
      employee.setMobileNo(mobile);
      employee.setPassword(pass);

      employeeApi.save(employee)
          .enqueue(new Callback<EmployeeDTO>() {
            @Override
            public void onResponse(Call<EmployeeDTO> call, Response<EmployeeDTO> response) {

              Toast.makeText(EmployeeForm.this, "Save successful! ", Toast.LENGTH_SHORT).show();

              Intent intent = new Intent(EmployeeForm.this, EmployeeListActivity.class);
              startActivity(intent);
            }

            @Override
            public void onFailure(Call<EmployeeDTO> call, Throwable t) {
              Toast.makeText(EmployeeForm.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
              Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
          });
    });
  }
}