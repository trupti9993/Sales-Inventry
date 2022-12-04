package com.sales_inventry.springclient.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sales_inventry.springclient.R;

public class ProductHolder extends RecyclerView.ViewHolder {

  TextView name, prodType, prodUnit;
  FloatingActionButton deleteBtn,updateBtn;

  public ProductHolder(@NonNull View itemView) {
    super(itemView);
    name = itemView.findViewById(R.id.productListItem_name);
    prodType = itemView.findViewById(R.id.productListItem_prodType);
    prodUnit = itemView.findViewById(R.id.productListItem_prodUnit);
    deleteBtn= itemView.findViewById(R.id.productListItem_deleteBtn);
    updateBtn= itemView.findViewById(R.id.productListItem_updateBtn);

  }
}
