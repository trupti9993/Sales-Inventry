package com.sales_inventry.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.PaymentDTO;
import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.PaymentApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentForm extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.create_payment);

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
      PaymentApi paymentApi = retrofitService.getRetrofit().create(PaymentApi.class);

   if(PaymentListActivity.getPaymentId()!=-1){
       paymentApi.getPayment(PaymentListActivity.getPaymentId())
               .enqueue(new Callback<ResponseEntity>() {
                   @Override
                   public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                       PaymentDTO paymentDTO= (PaymentDTO) response.body().getPaymentData();
/*
                       inputEditTextName.setText(employeeDTO.getEmpName());
                       inputEditMobileNo.setText(employeeDTO.getMobileNo());
                       inputEditEmail.setText(employeeDTO.getEmail());
                       inputEditAddress.setText(employeeDTO.getAddress());
                       inputEditPass.setText(employeeDTO.getPassword());
                       inputEditTextId.setText(String.valueOf( employeeDTO.getEmployeeId()));*/
                   }

                   @Override
                   public void onFailure(Call<ResponseEntity> call, Throwable t) {
                       Toast.makeText(PaymentForm.this, "Payment Fetch failed!!!", Toast.LENGTH_SHORT).show();
                       Logger.getLogger(PaymentForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
                   }
               });
       PaymentListActivity.setPaymentId(-1);
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
      Integer paymentId=Integer.parseInt(String.valueOf(inputEditTextId.getText()));

        PaymentDTO payment = new PaymentDTO();
      /*employee.setEmpName(name);
        employee.setEmployeeId(employeeId);
      employee.setAddress(address);
      employee.setEmail(email);
      employee.setMobileNo(mobile);
      employee.setPassword(pass);*/

        paymentApi.savePayment(payment)
          .enqueue(new Callback<PaymentDTO>() {
            @Override
            public void onResponse(Call<PaymentDTO> call, Response<PaymentDTO> response) {

              Toast.makeText(PaymentForm.this, "Save successful! ", Toast.LENGTH_SHORT).show();

              Intent intent = new Intent(PaymentForm.this, PaymentListActivity.class);
              startActivity(intent);
            }

            @Override
            public void onFailure(Call<PaymentDTO> call, Throwable t) {
              Toast.makeText(PaymentForm.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
              Logger.getLogger(PaymentForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
          });
    });
  }
}