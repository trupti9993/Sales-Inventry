 package com.sales_inventry.springclient;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    RetrofitService retrofitService = new RetrofitService();
    ProductApi productApi = retrofitService.getRetrofit().create(ProductApi.class);
    PartyApi partyApi = retrofitService.getRetrofit().create(PartyApi.class);
    EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);
    SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
    Spinner productDropDown;

    Spinner partyDropDown;

    Spinner employeeDropDown;

    TextInputEditText inputEditTextQyantity ;

    TextInputEditText inputEditTextRate ;

    TextInputEditText inputEditAmount ;

    TextInputEditText inputEditDiscount ;

    TextInputEditText inputEditTax ;

    TextInputEditText inputEditNetAmount ;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.create_purchase);

    initializeComponents();
  }

  private void initializeComponents() {

      TextInputEditText inputEditTextId = findViewById(R.id.form_textFieldId);

      EditText inputEditTextDate = findViewById(R.id.form_textFieldDate);

      productDropDown = (Spinner) findViewById(R.id.form_textFieldProduct);
      productDropDown.setOnItemSelectedListener(this);

      partyDropDown= (Spinner) findViewById(R.id.form_textFieldParty);
      partyDropDown.setOnItemSelectedListener(this);

      employeeDropDown= (Spinner) findViewById(R.id.form_textFieldEmployee);
      employeeDropDown.setOnItemSelectedListener(this);


      inputEditTextId.setVisibility(View.INVISIBLE);


      LocalDate now = LocalDate.now();
      inputEditTextDate.setText(now.getDayOfMonth()+"/"+now.getMonthValue()+"/"+now.getYear());

       inputEditTextQyantity = findViewById(R.id.form_textFieldQuantity);

      inputEditTextQyantity.setOnClickListener(this);

       inputEditTextRate = findViewById(R.id.form_textFieldRate);

      inputEditTextRate.setOnClickListener(this);

      inputEditAmount = findViewById(R.id.form_textFieldAmount);

      inputEditAmount.setOnClickListener(this);

       inputEditDiscount = findViewById(R.id.form_textFieldDiscount);

      inputEditDiscount.setOnClickListener(this);

       inputEditTax = findViewById(R.id.form_textFieldTax);
      inputEditTax.setOnClickListener(this);

       inputEditNetAmount = findViewById(R.id.form_textFieldNetAmount);
      inputEditNetAmount.setOnClickListener(this);

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

                   }

                   @Override
                   public void onFailure(Call<ResponseEntity> call, Throwable t) {
                       Toast.makeText(PurchaseForm.this, "Purchase Fetch failed..!", Toast.LENGTH_SHORT).show();
                       Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
                   }
               });
       PurchaseListActivity.setPurchaseId(-1);
    }else {

       inputEditTextId.setText("-1");
   }




    buttonSave.setOnClickListener(view -> {
    try{
            Integer purchaseId=Integer.parseInt(String.valueOf(inputEditTextId.getText()));

            String dateStr = String.valueOf(inputEditTextDate.getText());
            Date date=formatter.parse(dateStr);

        Integer productId=  ((ProductDTO)productDropDown.getSelectedItem()).getProdId();

            Integer partyId= ((PartyDTO)partyDropDown.getSelectedItem()).getPartyId();

            Integer empId= ((EmployeeDTO)employeeDropDown.getSelectedItem()).getEmployeeId();


            String qty = String.valueOf(inputEditTextQyantity.getText());
            Double quantity=  Double.parseDouble(qty.equals("")?"0":qty);

            String rateStr = String.valueOf(inputEditTextRate.getText());
            Double rate= Double.parseDouble(rateStr.equals("")?"0":rateStr);

            String amt = String.valueOf(inputEditAmount.getText());
            Double amount= Double.parseDouble( amt.equals("")?"0":amt);


            String dis = String.valueOf(inputEditDiscount.getText());
            Double discount= Double.parseDouble(dis.equals("")?"0":dis);

            String taxStr= String.valueOf(inputEditTax.getText());
            Double tax= Double.parseDouble(taxStr.equals("")?"0":taxStr);

            String rateAmt = String.valueOf(inputEditNetAmount.getText());
            Double netAmount= Double.parseDouble(rateAmt.equals("")?"0":rateAmt);

            if(quantity.equals(0.0)){
                Toast.makeText(PurchaseForm.this, "Please Enter Quantity..! ", Toast.LENGTH_SHORT).show();
                inputEditTextQyantity.requestFocus();
                return;
            }

            if(rate.equals(0.0)){
                Toast.makeText(PurchaseForm.this, "Please Enter Rate..! ", Toast.LENGTH_SHORT).show();
                inputEditTextRate.requestFocus();
                return;
            }

            if(amt.equals(0.0)){
                Toast.makeText(PurchaseForm.this, "Please Enter Amount..! ", Toast.LENGTH_SHORT).show();
                inputEditAmount.requestFocus();
                return;
            }

            if(discount.equals(0.0)){
                Toast.makeText(PurchaseForm.this, "Please Enter Discount..! ", Toast.LENGTH_SHORT).show();
                inputEditDiscount.requestFocus();
                return;
            }

            if(tax.equals(0.0)){
                Toast.makeText(PurchaseForm.this, "Please Enter Tax..! ", Toast.LENGTH_SHORT).show();
                inputEditTax.requestFocus();
                return;
            }

            if(netAmount.equals(0.0)){
                Toast.makeText(PurchaseForm.this, "Please Enter Net Amount..! ", Toast.LENGTH_SHORT).show();
                inputEditNetAmount.requestFocus();
                return;
            }

             PurchaseDTO purchase = new PurchaseDTO();
             purchase.setPurchaseId(purchaseId);
             purchase.setEmpId(empId);
             purchase.setProdId(productId);
             purchase.setPartyId(partyId);

             purchase.setQuantity(quantity);
             purchase.setRate(rate);
             purchase.setAmount(amount);

             purchase.setDiscount(discount);
             purchase.setTax(tax);

             purchase.setNetAmount(netAmount);

          purchaseApi.savePurchase(purchase)
              .enqueue(new Callback<PurchaseDTO>() {
                @Override
                public void onResponse(Call<PurchaseDTO> call, Response<PurchaseDTO> response) {

                  Toast.makeText(PurchaseForm.this, "Purchase Save successful..! ", Toast.LENGTH_SHORT).show();

                  Intent intent = new Intent(PurchaseForm.this, PurchaseListActivity.class);
                  startActivity(intent);
                }

                @Override
                public void onFailure(Call<PurchaseDTO> call, Throwable t) {
                  Toast.makeText(PurchaseForm.this, "Purchase Save failed..!", Toast.LENGTH_SHORT).show();
                  Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
                }
              });
    } catch(Exception e){
        Toast.makeText(PurchaseForm.this, "Purchase Save failed..! "+ e, Toast.LENGTH_SHORT).show();
    }
    });

  }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch(adapterView.getId())
        {
            case R.id.form_textFieldProduct:
                ProductDTO prod = (ProductDTO) adapterView.getSelectedItem();

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
                                Toast.makeText(PurchaseForm.this, "Failed to load Party data..!", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseEntity> call, Throwable t) {
                            Toast.makeText(PurchaseForm.this, "Failed to load Party data..! ", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(PurchaseForm.this, "Failed to load Employee data..!", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseEntity> call, Throwable t) {
                            Toast.makeText(PurchaseForm.this, "Failed to load Employee data..! ", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(PurchaseForm.this, "Failed to load Product data..!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {
                        Toast.makeText(PurchaseForm.this, "Failed to load Product data..! ", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View view) {
        String qty = String.valueOf(inputEditTextQyantity.getText());
        Double quantity=  Double.parseDouble(qty.equals("")?"0":qty);

        String rateStr = String.valueOf(inputEditTextRate.getText());
        Double rate= Double.parseDouble(rateStr.equals("")?"0":rateStr);


        Double amount= quantity*rate;

        inputEditAmount.setText(String.valueOf(amount));


        String dis = String.valueOf(inputEditDiscount.getText());
        Double discount= Double.parseDouble(dis.equals("")?"0":dis);

        Double discAmt=amount*discount/100;

        String taxStr= String.valueOf(inputEditTax.getText());
        Double tax= Double.parseDouble(taxStr.equals("")?"0":taxStr);

        double amountAfterDiscount = amount - discAmt;

        Double taxAmt= amountAfterDiscount * tax/100 ;

        Double netAmount= amountAfterDiscount+taxAmt;

        inputEditNetAmount.setText(String.valueOf(netAmount));
    }
}