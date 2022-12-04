package com.sales_inventry.springclient.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sales_inventry.springclient.R;

public class ReceiptHolder extends RecyclerView.ViewHolder {

  TextView name, address, email;
  FloatingActionButton deleteBtn,updateBtn;

  public ReceiptHolder(@NonNull View itemView) {
    super(itemView);
    name = itemView.findViewById(R.id.employeeListItem_name);
    email = itemView.findViewById(R.id.employeeListItem_email);
    address = itemView.findViewById(R.id.employeeListItem_address);
    deleteBtn= itemView.findViewById(R.id.employeeListItem_deleteBtn);
    updateBtn= itemView.findViewById(R.id.employeeListItem_updateBtn);

  }
}
