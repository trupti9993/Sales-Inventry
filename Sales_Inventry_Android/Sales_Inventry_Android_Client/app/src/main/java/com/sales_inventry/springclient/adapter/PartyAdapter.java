package com.sales_inventry.springclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sales_inventry.springclient.PartyForm;
import com.sales_inventry.springclient.PartyListActivity;
import com.sales_inventry.springclient.R;
import com.sales_inventry.springclient.model.PartyDTO;
import com.sales_inventry.springclient.reotrfit.PartyApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PartyAdapter extends RecyclerView.Adapter<PartyHolder> {

  private final List<PartyDTO> partyList;

  private final PartyListActivity partyListActivity;

  public PartyAdapter(List<PartyDTO> partyList, PartyListActivity partyListActivity) {
    this.partyList = partyList;
    this.partyListActivity=partyListActivity;
  }

  @NonNull
  @Override
  public PartyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_party_item, parent, false);
    return new PartyHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull PartyHolder holder, int position) {

    RetrofitService retrofitService = new RetrofitService();
    PartyApi partyApi = retrofitService.getRetrofit().create(PartyApi.class);


try {
  PartyDTO party = partyList.get(position);
  Integer partyId=party.getPartyId();
  holder.name.setText(party.getPartyName());
  holder.address.setText(party.getAddress());
  holder.email.setText(party.getEmail());

  holder.updateBtn.setOnClickListener(view -> partyListActivity.updateParty(partyId));
  holder.deleteBtn.setOnClickListener(view -> partyListActivity.deleteParty(partyId));
} catch (Exception e){
  Toast.makeText(null, "Save failed!!!", Toast.LENGTH_SHORT).show();
  Logger.getLogger(PartyForm.class.getName()).log(Level.SEVERE, "Error occurred", e);
}


  }

  @Override
  public int getItemCount() {
    return partyList.size();
  }
}
