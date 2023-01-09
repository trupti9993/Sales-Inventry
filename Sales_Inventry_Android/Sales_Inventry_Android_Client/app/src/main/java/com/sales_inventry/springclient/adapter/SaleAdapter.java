package com.sales_inventry.springclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sales_inventry.springclient.R;
import com.sales_inventry.springclient.SaleForm;
import com.sales_inventry.springclient.SaleListActivity;
import com.sales_inventry.springclient.model.SalesDTO;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleAdapter extends RecyclerView.Adapter<SaleHolder> {

  private final List<SalesDTO> saleList;

  private final SaleListActivity saleListActivity;

  private SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");

  public SaleAdapter(List<SalesDTO> saleList, SaleListActivity saleListActivity) {
    this.saleList = saleList;
    this.saleListActivity=saleListActivity;
  }

  @NonNull
  @Override
  public SaleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_sale_item, parent, false);
    return new SaleHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull SaleHolder holder, int position) {

        try {
          SalesDTO sale = saleList.get(position);
          Integer salesId=sale.getSalesId();
          holder.id.setText(String.valueOf(salesId));

          holder.date.setText(formatter.format(sale.getDate()));
          holder.amount.setText(String.valueOf(sale.getNetAmount()));

          holder.updateBtn.setOnClickListener(view -> saleListActivity.updateSale(salesId));
          holder.deleteBtn.setOnClickListener(view -> saleListActivity.deleteSale(salesId));
        } catch (Exception e){
          Toast.makeText(null, "Failed to fetch Sale data..!", Toast.LENGTH_SHORT).show();
          Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, "Error occurred", e);
        }


  }

  @Override
  public int getItemCount() {
    return saleList.size();
  }
}
