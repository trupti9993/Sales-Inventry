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
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.PartyDTO;

import com.sales_inventry.springclient.model.ReceiptDTO;
import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.model.SalesDTO;

import com.sales_inventry.springclient.reotrfit.ReceiptApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;
import com.sales_inventry.springclient.reotrfit.SaleApi;
import com.sales_inventry.springclient.reotrfit.PartyApi;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiptForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    RetrofitService retrofitService = new RetrofitService();
    SaleApi saleApi = retrofitService.getRetrofit().create(SaleApi.class);
    PartyApi partyApi = retrofitService.getRetrofit().create(PartyApi.class);
    EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    Spinner saleDropDown;

    Spinner partyDropDown;

    Spinner employeeDropDown;


    TextInputEditText inputEditAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_recipt);

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditTextId = findViewById(R.id.form_textFieldId);

        EditText inputEditTextDate = findViewById(R.id.form_textFieldDate);

        saleDropDown = (Spinner) findViewById(R.id.form_textFieldSale);
        saleDropDown.setOnItemSelectedListener(this);

        partyDropDown = (Spinner) findViewById(R.id.form_textFieldParty);
        partyDropDown.setOnItemSelectedListener(this);

        employeeDropDown = (Spinner) findViewById(R.id.form_textFieldEmployee);
        employeeDropDown.setOnItemSelectedListener(this);


        inputEditTextId.setVisibility(View.INVISIBLE);


        LocalDate now = LocalDate.now();
        inputEditTextDate.setText(now.getDayOfMonth() + "/" + now.getMonthValue() + "/" + now.getYear());

        inputEditAmount = findViewById(R.id.form_textFieldAmount);


        MaterialButton buttonSave = findViewById(R.id.form_buttonSave);


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
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReceiptForm.this,
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
        ReceiptApi receiptApi = retrofitService.getRetrofit().create(ReceiptApi.class);

        if (ReceiptListActivity.getReceiptId() != -1) {
            receiptApi.getReceipt(ReceiptListActivity.getReceiptId())
                    .enqueue(new Callback<ResponseEntity>() {
                        @Override
                        public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                            ReceiptDTO receiptDTO = (ReceiptDTO) response.body().getReceiptData();
                            inputEditTextId.setText(String.valueOf(receiptDTO.getReceiptId()));
                            inputEditAmount.setText(String.valueOf(receiptDTO.getAmount()));
                        }

                        @Override
                        public void onFailure(Call<ResponseEntity> call, Throwable t) {
                            Toast.makeText(ReceiptForm.this, "Receipt Fetch failed..!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(ReceiptForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        }
                    });
            ReceiptListActivity.setReceiptId(-1);
        } else {

            inputEditTextId.setText("-1");
        }


        buttonSave.setOnClickListener(view -> {
            try {
                Integer receiptId = Integer.parseInt(String.valueOf(inputEditTextId.getText()));

                String dateStr = String.valueOf(inputEditTextDate.getText());
                Date date = formatter.parse(dateStr);

                Integer salesId = ((SalesDTO) saleDropDown.getSelectedItem()).getSalesId();

                Integer partyId = ((PartyDTO) partyDropDown.getSelectedItem()).getPartyId();

                Integer empId = ((EmployeeDTO) employeeDropDown.getSelectedItem()).getEmployeeId();


                String amt = String.valueOf(inputEditAmount.getText());
                Double amount = Double.parseDouble(amt.equals("") ? "0" : amt);

                if(amt.trim().equals("")){
                    Toast.makeText(ReceiptForm.this, "Please Enter Amount..! ", Toast.LENGTH_SHORT).show();
                    inputEditAmount.requestFocus();
                    return;
                }
                ReceiptDTO receipt = new ReceiptDTO();
                receipt.setSalesId(salesId);
                receipt.setEmpId(empId);
                receipt.setReceiptId(receiptId);
                receipt.setPartyId(partyId);

                receipt.setAmount(amount);


                receiptApi.saveReceipt(receipt)
                        .enqueue(new Callback<ReceiptDTO>() {
                            @Override
                            public void onResponse(Call<ReceiptDTO> call, Response<ReceiptDTO> response) {

                                Toast.makeText(ReceiptForm.this, "Receipt Save successful..! ", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(ReceiptForm.this, ReceiptListActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<ReceiptDTO> call, Throwable t) {
                                Toast.makeText(ReceiptForm.this, "Receipt Save failed..!", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(ReceiptForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
                            }
                        });
            } catch (Exception e) {
                Toast.makeText(ReceiptForm.this, "Receipt Save failed..! " + e, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {
            case R.id.form_textFieldSale:
                SalesDTO sale = (SalesDTO) adapterView.getSelectedItem();
                inputEditAmount.setText(String.valueOf(sale.getNetAmount()));



                partyApi.getParty(sale.getPartyId())
                        .enqueue(new Callback<ResponseEntity>() {
                            @Override
                            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                                PartyDTO partyDTO= (PartyDTO) response.body().getPartyData();
                                List<PartyDTO> responseData = new ArrayList<>();
                                responseData.add(partyDTO);
                                populatePartyListView(responseData);
                            }

                            @Override
                            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                                Toast.makeText(ReceiptForm.this, "Failed to load Party data..!", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(PartyForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
                            }
                        });


                //  Toast.makeText(getApplicationContext(), prod.getProdName() +" >> ", Toast.LENGTH_LONG).show();
                break;

            case R.id.form_textFieldParty:
                PartyDTO party = (PartyDTO) adapterView.getSelectedItem();

                //Toast.makeText(getApplicationContext(), party.getPartyName() +" >> ", Toast.LENGTH_LONG).show();
                break;

            case R.id.form_textFieldEmployee:
                EmployeeDTO emp = (EmployeeDTO) adapterView.getSelectedItem();

                //Toast.makeText(getApplicationContext(), emp.getEmpName() +" >> ", Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllPurchaseData();
        loadAllEmployee();
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
                            } catch (Exception e) {
                                Toast.makeText(ReceiptForm.this, "Failed to load Employee data..!" , Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseEntity> call, Throwable t) {
                            Toast.makeText(ReceiptForm.this, "Failed to load Employee data..! ", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void getAllPurchaseData() {

        saleApi.getAllSalesDataForReceipt()
                .enqueue(new Callback<ResponseEntity>() {
                    @Override
                    public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                        try {
                            List<SalesDTO> responseData = (List<SalesDTO>) response.body().getSaleResponseData();

                            populateSaleListView(responseData);
                        } catch (Exception e) {
                            Toast.makeText(ReceiptForm.this, "Failed to load Sale data..! ", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {
                        Toast.makeText(ReceiptForm.this, "Failed to load Sale data..! ", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void populateEmployeeListView(List<EmployeeDTO> employeeList) {

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, employeeList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        employeeDropDown.setAdapter(aa);
    }

    private void populatePartyListView(List<PartyDTO> partyList) {

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, partyList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        partyDropDown.setAdapter(aa);
    }

    private void populateSaleListView(List<SalesDTO> saleList) {

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, saleList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        saleDropDown.setAdapter(aa);
    }
}