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
import com.sales_inventry.springclient.model.PaymentDTO;
import com.sales_inventry.springclient.model.ProductDTO;
import com.sales_inventry.springclient.model.PurchaseDTO;
import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.PartyApi;
import com.sales_inventry.springclient.reotrfit.PaymentApi;
import com.sales_inventry.springclient.reotrfit.ProductApi;
import com.sales_inventry.springclient.reotrfit.PurchaseApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RetrofitService retrofitService = new RetrofitService();
    PurchaseApi purchaseApi = retrofitService.getRetrofit().create(PurchaseApi.class);
    PartyApi partyApi = retrofitService.getRetrofit().create(PartyApi.class);
    EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);
    SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");

    Spinner purchaseDropDown;

    Spinner partyDropDown;

    Spinner employeeDropDown;



    TextInputEditText inputEditAmount ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_payment);

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditTextId = findViewById(R.id.form_textFieldId);

        EditText inputEditTextDate = findViewById(R.id.form_textFieldDate);

        purchaseDropDown = (Spinner) findViewById(R.id.form_textFieldPurchase);
        purchaseDropDown.setOnItemSelectedListener(this);

        partyDropDown= (Spinner) findViewById(R.id.form_textFieldParty);
        partyDropDown.setOnItemSelectedListener(this);

        employeeDropDown= (Spinner) findViewById(R.id.form_textFieldEmployee);
        employeeDropDown.setOnItemSelectedListener(this);


        inputEditTextId.setVisibility(View.INVISIBLE);


        LocalDate now = LocalDate.now();
        inputEditTextDate.setText(now.getDayOfMonth()+"/"+now.getMonthValue()+"/"+now.getYear());

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(PaymentForm.this,
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
        PaymentApi paymentApi = retrofitService.getRetrofit().create(PaymentApi.class);

        if(PaymentListActivity.getPaymentId()!=-1){
            paymentApi.getPayment(PaymentListActivity.getPaymentId())
                    .enqueue(new Callback<ResponseEntity>() {
                        @Override
                        public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                            PaymentDTO paymentDTO= (PaymentDTO) response.body().getPaymentData();

                        }

                        @Override
                        public void onFailure(Call<ResponseEntity> call, Throwable t) {
                            Toast.makeText(PaymentForm.this, "Payment Fetch failed!!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(PaymentForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        }
                    });
            PaymentListActivity.setPaymentId(-1);
        }else {

            inputEditTextId.setText("-1");
        }




        buttonSave.setOnClickListener(view -> {
            try{
                Integer paymentId=Integer.parseInt(String.valueOf(inputEditTextId.getText()));

                String dateStr = String.valueOf(inputEditTextDate.getText());
                Date date=formatter.parse(dateStr);

                Integer purchaseId=  ((PurchaseDTO)purchaseDropDown.getSelectedItem()).getPurchaseId();

                Integer partyId= ((PartyDTO)partyDropDown.getSelectedItem()).getPartyId();

                Integer empId= ((EmployeeDTO)employeeDropDown.getSelectedItem()).getEmployeeId();



                String amt = String.valueOf(inputEditAmount.getText());
                Double amount= Double.parseDouble( amt.equals("")?"0":amt);

                if(amt.trim().equals("")){
                    Toast.makeText(PaymentForm.this, "Please Enter Amount..! ", Toast.LENGTH_SHORT).show();
                    inputEditAmount.requestFocus();
                    return;
                }



                PaymentDTO payment = new PaymentDTO();
                payment.setPurchaseId(purchaseId);
                payment.setEmpId(empId);
                payment.setPaymentId(paymentId);
                payment.setPartyId(partyId);

                payment.setAmount(amount);




                paymentApi.savePayment(payment)
                        .enqueue(new Callback<PaymentDTO>() {
                            @Override
                            public void onResponse(Call<PaymentDTO> call, Response<PaymentDTO> response) {

                                Toast.makeText(PaymentForm.this, "Payment Save successful..! ", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(PaymentForm.this, PaymentListActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<PaymentDTO> call, Throwable t) {
                                Toast.makeText(PaymentForm.this, "Payment Save failed..!", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(PaymentForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
                            }
                        });
            } catch(Exception e){
                Toast.makeText(PaymentForm.this, "Payment Save failed!!! "+ e, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch(adapterView.getId())
        {
            case R.id.form_textFieldProduct:
                PurchaseDTO purchase = (PurchaseDTO) adapterView.getSelectedItem();

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
                                Toast.makeText(PaymentForm.this, "Failed to load Party data..!", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseEntity> call, Throwable t) {
                            Toast.makeText(PaymentForm.this, "Failed to load Party data..! ", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(PaymentForm.this, "Failed to load Employee data..!", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseEntity> call, Throwable t) {
                            Toast.makeText(PaymentForm.this, "Failed to load Employee data..! ", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void getAllPurchaseData() {

        purchaseApi.getAllPurchaseData()
                .enqueue(new Callback<ResponseEntity>() {
                    @Override
                    public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                        try {
                            List<PurchaseDTO> responseData = (List<PurchaseDTO>) response.body().getPurchaseResponseData();

                            populateProductListView(responseData);
                        }catch (Exception e){
                            Toast.makeText(PaymentForm.this, "Failed to load Purchase data..! ", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {
                        Toast.makeText(PaymentForm.this, "Failed to load Purchase data..! ", Toast.LENGTH_SHORT).show();
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

    private void populateProductListView(List<PurchaseDTO> purchaseList) {

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,purchaseList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        purchaseDropDown.setAdapter(aa);
    }


}