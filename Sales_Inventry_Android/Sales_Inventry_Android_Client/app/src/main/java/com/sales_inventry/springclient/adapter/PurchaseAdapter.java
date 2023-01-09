package com.sales_inventry.springclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sales_inventry.springclient.EmployeeForm;
import com.sales_inventry.springclient.EmployeeListActivity;
import com.sales_inventry.springclient.PurchaseForm;
import com.sales_inventry.springclient.PurchaseListActivity;
import com.sales_inventry.springclient.R;
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.PurchaseDTO;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.PurchaseApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseHolder> {

  private final List<PurchaseDTO> purchaseList;

  private final PurchaseListActivity   purchaseListActivity;

  private SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");

  public PurchaseAdapter(List<PurchaseDTO> purchaseList, PurchaseListActivity purchaseListActivity) {
    this.purchaseList = purchaseList;
    this.purchaseListActivity=purchaseListActivity;
  }

  @NonNull
  @Override
  public PurchaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_purchase_item, parent, false);
    return new PurchaseHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull PurchaseHolder holder, int position) {

    RetrofitService retrofitService = new RetrofitService();
    PurchaseApi purchaseApi = retrofitService.getRetrofit().create(PurchaseApi.class);


try {
  PurchaseDTO purchase = purchaseList.get(position);
  Integer purchaseId=purchase.getPurchaseId();

  holder.id.setText(String.valueOf(purchaseId));

  holder.date.setText(formatter.format(purchase.getDate()));
  holder.amount.setText(String.valueOf(purchase.getNetAmount()));

  holder.updateBtn.setOnClickListener(view -> purchaseListActivity.updatePurchase(purchaseId));
  holder.deleteBtn.setOnClickListener(view -> purchaseListActivity.deletePurchase(purchaseId));
} catch (Exception e){
  Toast.makeText(null, "Save failed!!!", Toast.LENGTH_SHORT).show();
  Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, "Error occurred", e);
}


  }

  @Override
  public int getItemCount() {
    return purchaseList.size();
  }
}
