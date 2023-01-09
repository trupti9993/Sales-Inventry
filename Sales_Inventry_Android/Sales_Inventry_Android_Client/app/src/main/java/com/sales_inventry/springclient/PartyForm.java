package com.sales_inventry.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.PartyDTO;
import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.PartyApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartyForm extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.create_party);

    initializeComponents();
  }

  private void initializeComponents() {
    TextInputEditText inputEditTextName = findViewById(R.id.form_textFieldName);
    TextInputEditText inputEditTextId = findViewById(R.id.form_textFieldId);
    TextInputEditText inputEditMobileNo = findViewById(R.id.form_textFieldMobileNo);
    TextInputEditText inputEditEmail = findViewById(R.id.form_textFieldEmail);
    TextInputEditText inputEditAddress = findViewById(R.id.form_textFieldAddress);

    MaterialButton buttonSave = findViewById(R.id.form_buttonSave);

     inputEditTextId.setVisibility(View.INVISIBLE);

      RetrofitService retrofitService = new RetrofitService();
      PartyApi partyApi = retrofitService.getRetrofit().create(PartyApi.class);

   if(PartyListActivity.getPartyId()!=-1){
       partyApi.getParty(PartyListActivity.getPartyId())
               .enqueue(new Callback<ResponseEntity>() {
                   @Override
                   public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                       PartyDTO partyDTO= (PartyDTO) response.body().getPartyData();

                       inputEditTextName.setText(partyDTO.getPartyName());
                       inputEditMobileNo.setText(partyDTO.getMobileNo());
                       inputEditEmail.setText(partyDTO.getEmail());
                       inputEditAddress.setText(partyDTO.getAddress());

                       inputEditTextId.setText(String.valueOf( partyDTO.getPartyId()));
                   }

                   @Override
                   public void onFailure(Call<ResponseEntity> call, Throwable t) {
                       Toast.makeText(PartyForm.this, "Failed to load Party data..!", Toast.LENGTH_SHORT).show();
                       Logger.getLogger(PartyForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
                   }
               });
       PartyListActivity.setPartyId(-1);
    }else {
       inputEditTextName.setText("");
       inputEditMobileNo.setText("");
       inputEditEmail.setText("");
       inputEditAddress.setText("");

       inputEditTextId.setText("-1");
   }




    buttonSave.setOnClickListener(view -> {
      String name = String.valueOf(inputEditTextName.getText());
      String address = String.valueOf(inputEditAddress.getText());
      String email = String.valueOf(inputEditEmail.getText());
      String mobile = String.valueOf(inputEditMobileNo.getText());

      Integer partyId=Integer.parseInt(String.valueOf(inputEditTextId.getText()));

        if(name.trim().equals("")){
            Toast.makeText(PartyForm.this, "Please Enter Name..! ", Toast.LENGTH_SHORT).show();
            inputEditTextName.requestFocus();
            return;
        }

        if(mobile.trim().equals("")){
            Toast.makeText(PartyForm.this, "Please Enter Mobile No..! ", Toast.LENGTH_SHORT).show();
            inputEditMobileNo.requestFocus();
            return;
        }

        if(email.trim().equals("")){
            Toast.makeText(PartyForm.this, "Please Enter Email..! ", Toast.LENGTH_SHORT).show();
            inputEditEmail.requestFocus();
            return;
        }

        if(address.trim().equals("")){
            Toast.makeText(PartyForm.this, "Please Enter Address..! ", Toast.LENGTH_SHORT).show();
            inputEditAddress.requestFocus();
            return;
        }

        PartyDTO party = new PartyDTO();
        party.setFirstName(name);
        party.setPartyId(partyId);
        party.setAddress(address);
        party.setEmail(email);
        party.setMobileNo(mobile);


        partyApi.saveParty(party)
          .enqueue(new Callback<PartyDTO>() {
            @Override
            public void onResponse(Call<PartyDTO> call, Response<PartyDTO> response) {

              Toast.makeText(PartyForm.this, "Party Save successful..! ", Toast.LENGTH_SHORT).show();

              Intent intent = new Intent(PartyForm.this, PartyListActivity.class);
              startActivity(intent);
            }

            @Override
            public void onFailure(Call<PartyDTO> call, Throwable t) {
              Toast.makeText(PartyForm.this, "Party Save failed..!", Toast.LENGTH_SHORT).show();
              Logger.getLogger(PartyForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
          });
    });
  }
}