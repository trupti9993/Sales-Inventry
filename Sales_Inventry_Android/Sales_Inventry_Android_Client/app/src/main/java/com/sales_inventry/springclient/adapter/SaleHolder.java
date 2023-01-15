package com.sales_inventry.springclient.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sales_inventry.springclient.R;

public class SaleHolder extends RecyclerView.ViewHolder {

  TextView id, date, amount;
  FloatingActionButton deleteBtn,updateBtn;

  public SaleHolder(@NonNull View itemView) {
    super(itemView);
    id = itemView.findViewById(R.id.saleListItem_id);
    date = itemView.findViewById(R.id.saleListItem_date);
    amount = itemView.findViewById(R.id.saleListItem_amount);
    deleteBtn= itemView.findViewById(R.id.saleListItem_deleteBtn);
    updateBtn= itemView.findViewById(R.id.saleListItem_updateBtn);

  }
}
