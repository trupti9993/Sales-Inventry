package com.sales_inventry.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sales_inventry.springclient.adapter.PartyAdapter;
import com.sales_inventry.springclient.model.PartyDTO;
import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.reotrfit.PartyApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartyListActivity extends AppCompatActivity {

    private static Integer partyId=-1;
    public static Integer getPartyId() {
        return partyId;
    }

    public static void setPartyId(Integer id) {
        partyId=id;
    }

  private RecyclerView recyclerView;

    RetrofitService retrofitService = new RetrofitService();
    PartyApi partyApi = retrofitService.getRetrofit().create(PartyApi.class);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_party_list);

    recyclerView = findViewById(R.id.employeeList_recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    FloatingActionButton floatingActionButton = findViewById(R.id.employeeList_fab);
    floatingActionButton.setOnClickListener(view -> {
      Intent intent = new Intent(this, PartyForm.class);
      startActivity(intent);
    });
  }

  private void loadAllParty() {

      partyApi.getAllParty()
        .enqueue(new Callback<ResponseEntity>() {
          @Override
          public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
           try {
               List<PartyDTO> responseData = (List<PartyDTO>) response.body().getPartyResponseData();

               populateListView(responseData);
           }catch (Exception e){
               Toast.makeText(PartyListActivity.this, "Failed to load Party data..!", Toast.LENGTH_SHORT).show();

           }
          }

          @Override
          public void onFailure(Call<ResponseEntity> call, Throwable t) {
            Toast.makeText(PartyListActivity.this, "Failed to load Party data..! ", Toast.LENGTH_SHORT).show();
          }
        });
  }


  private void populateListView(List<PartyDTO> partyList) {
      PartyAdapter partyAdapter = new PartyAdapter(partyList,this);
      recyclerView.setAdapter(partyAdapter);
  }

  @Override
  protected void onResume() {
    super.onResume();
      loadAllParty();
  }

    public void deleteParty(Integer partyId) {
        partyApi.deleteParty(partyId).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {

                Toast.makeText(PartyListActivity.this, "Party Delete successful..! ", Toast.LENGTH_SHORT).show();

                loadAllParty();
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {

                Toast.makeText(PartyListActivity.this, "Party Delete failed..!", Toast.LENGTH_SHORT).show();
                Logger.getLogger(PartyForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
        });


    }

    public void updateParty(Integer partyId) {
        PartyListActivity.setPartyId(partyId);
        Intent intent = new Intent(this, PartyForm.class);
        startActivity(intent);
    }
}