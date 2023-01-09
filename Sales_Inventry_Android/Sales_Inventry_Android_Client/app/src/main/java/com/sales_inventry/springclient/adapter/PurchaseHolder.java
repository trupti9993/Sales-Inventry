package com.sales_inventry.springclient.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sales_inventry.springclient.R;

public class PurchaseHolder extends RecyclerView.ViewHolder {

  TextView id, date, amount;
  FloatingActionButton deleteBtn,updateBtn;

  public PurchaseHolder(@NonNull View itemView) {
    super(itemView);
    id = itemView.findViewById(R.id.purchaseListItem_id);
    date = itemView.findViewById(R.id.purchaseListItem_date);
    amount = itemView.findViewById(R.id.purchaseListItem_amount);
    deleteBtn= itemView.findViewById(R.id.purchaseListItem_deleteBtn);
    updateBtn= itemView.findViewById(R.id.purchaseListItem_updateBtn);

  }
}
