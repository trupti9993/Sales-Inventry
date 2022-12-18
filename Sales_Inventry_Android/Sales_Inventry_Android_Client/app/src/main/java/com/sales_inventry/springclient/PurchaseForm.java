 package com.sales_inventry.springclient;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.sales_inventry.springclient.adapter.ProductAdapter;
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.PartyDTO;
import com.sales_inventry.springclient.model.ProductDTO;
import com.sales_inventry.springclient.model.PurchaseDTO;
import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.PartyApi;
import com.sales_inventry.springclient.reotrfit.ProductApi;
import com.sales_inventry.springclient.reotrfit.PurchaseApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RetrofitService retrofitService = new RetrofitService();
    ProductApi productApi = retrofitService.getRetrofit().create(ProductApi.class);
    PartyApi partyApi = retrofitService.getRetrofit().create(PartyApi.class);
    EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);

    Spinner productDropDown;

    Spinner partyDropDown;

    Spinner employeeDropDown;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.create_purchase);

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

      EditText inputEditTextDate = findViewById(R.id.form_textFieldDate);

      productDropDown = (Spinner) findViewById(R.id.form_textFieldProduct                  );
      productDropDown.setOnItemSelectedListener(this);

       partyDropDown= (Spinner) findViewById(R.id.form_textFieldParty                  );
      partyDropDown.setOnItemSelectedListener(this);

       employeeDropDown= (Spinner) findViewById(R.id.form_textFieldEmployee                  );
      employeeDropDown.setOnItemSelectedListener(this);


     inputEditTextId.setVisibility(View.INVISIBLE);

      // perform click event on edit text
      inputEditTextDate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              // calender class's instance and get current date , month and year from calender
              final Calendar c = Calendar.getInstance();
              int mYear = c.get(Calendar.YEAR); // current year
              int mMonth = c.get(Calendar.MONTH); // current month
              int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
              // date picker dialog
              DatePickerDialog     datePickerDialog = new DatePickerDialog(PurchaseForm.this,
                      new DatePickerDialog.OnDateSetListener() {

                          @Override
                          public void onDateSet(DatePicker view, int year,
                                                int monthOfYear, int dayOfMonth) {
                              // set day of month , month and year value in the edit text
                              inputEditTextDate.setText(dayOfMonth + "/"
                                      + (monthOfYear + 1) + "/" + year);

                          }
                      }, mYear, mMonth, mDay);
              datePickerDialog.show();
          }
      });



      RetrofitService retrofitService = new RetrofitService();
      PurchaseApi purchaseApi = retrofitService.getRetrofit().create(PurchaseApi.class);

   if(PurchaseListActivity.getPurchaseId()!=-1){
       purchaseApi.getPurchase(PurchaseListActivity.getPurchaseId())
               .enqueue(new Callback<ResponseEntity>() {
                   @Override
                   public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                       PurchaseDTO purchaseDTO= (PurchaseDTO) response.body().getPurchaseData();

                       /*inputEditTextName.setText(employeeDTO.getEmpName());
                       inputEditMobileNo.setText(employeeDTO.getMobileNo());
                       inputEditEmail.setText(employeeDTO.getEmail());
                       inputEditAddress.setText(employeeDTO.getAddress());
                       inputEditPass.setText(employeeDTO.getPassword());
                       inputEditTextId.setText(String.valueOf( employeeDTO.getEmployeeId()));*/
                   }

                   @Override
                   public void onFailure(Call<ResponseEntity> call, Throwable t) {
                       Toast.makeText(PurchaseForm.this, "Purchase Fetch failed!!!", Toast.LENGTH_SHORT).show();
                       Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
                   }
               });
       PurchaseListActivity.setPurchaseId(-1);
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
      Integer purchaseId=Integer.parseInt(String.valueOf(inputEditTextId.getText()));

      PurchaseDTO purchase = new PurchaseDTO();
      /*employee.setEmpName(name);
        employee.setEmployeeId(employeeId);
      employee.setAddress(address);
      employee.setEmail(email);
      employee.setMobileNo(mobile);
      employee.setPassword(pass);*/

      purchaseApi.savePurchase(purchase)
          .enqueue(new Callback<PurchaseDTO>() {
            @Override
            public void onResponse(Call<PurchaseDTO> call, Response<PurchaseDTO> response) {

              Toast.makeText(PurchaseForm.this, "Save successful! ", Toast.LENGTH_SHORT).show();

              Intent intent = new Intent(PurchaseForm.this, PurchaseListActivity.class);
              startActivity(intent);
            }

            @Override
            public void onFailure(Call<PurchaseDTO> call, Throwable t) {
              Toast.makeText(PurchaseForm.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
              Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
          });
    });
  }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch(adapterView.getId())
        {
            case R.id.form_textFieldProduct:
                ProductDTO prod = (ProductDTO) adapterView.getSelectedItem();

                Toast.makeText(getApplicationContext(), prod.getProdName() +" >> ", Toast.LENGTH_LONG).show();
                break;

            case R.id.form_textFieldParty:
                PartyDTO party = (PartyDTO) adapterView.getSelectedItem();

                Toast.makeText(getApplicationContext(), party.getPartyName() +" >> ", Toast.LENGTH_LONG).show();
                break;

            case R.id.form_textFieldEmployee:
                EmployeeDTO emp = (EmployeeDTO) adapterView.getSelectedItem();

                Toast.makeText(getApplicationContext(), emp.getEmpName() +" >> ", Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    protected void onResume() {
        super.onResume();
       loadAllProducts();
       loadAllParty();
       loadAllEmployee();
    }

    private void loadAllParty() {
        {

            partyApi.getAllParty()
                    .enqueue(new Callback<ResponseEntity>() {
                        @Override
                        public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                            try {
                                List<PartyDTO> responseData = (List<PartyDTO>) response.body().getPartyResponseData();

                                populatePartyListView(responseData);
                            }catch (Exception e){
                                Toast.makeText(PurchaseForm.this, "Save successful! "+e.toString(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseEntity> call, Throwable t) {
                            Toast.makeText(PurchaseForm.this, "Failed to load employees ", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void loadAllEmployee() {
        {

            employeeApi.getAllEmployees()
                    .enqueue(new Callback<ResponseEntity>() {
                        @Override
                        public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                            try {
                                List<EmployeeDTO> responseData = (List<EmployeeDTO>) response.body().getEmpResponseData();

                                populateEmployeeListView(responseData);
                            }catch (Exception e){
                                Toast.makeText(PurchaseForm.this, "Save successful! "+e.toString(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseEntity> call, Throwable t) {
                            Toast.makeText(PurchaseForm.this, "Failed to load employees ", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void loadAllProducts() {

        productApi.getAllProductData()
                .enqueue(new Callback<ResponseEntity>() {
                    @Override
                    public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                        try {
                            List<ProductDTO> responseData = (List<ProductDTO>) response.body().getProductResponseData();

                            populateProductListView(responseData);
                        }catch (Exception e){
                            Toast.makeText(PurchaseForm.this, "Save successful! "+e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {
                        Toast.makeText(PurchaseForm.this, "Failed to load employees ", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void populateEmployeeListView(List<EmployeeDTO> employeeList) {

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,employeeList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       //Setting the ArrayAdapter data on the Spinner
        employeeDropDown.setAdapter(aa);
    }

    private void populatePartyListView(List<PartyDTO> partyList) {

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,partyList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        partyDropDown.setAdapter(aa);
    }

    private void populateProductListView(List<ProductDTO> productList) {

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,productList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        productDropDown.setAdapter(aa);
    }
}