package com.sales_inventry.springclient.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.sales_inventry.springclient.R;

public class EmployeeHolder extends RecyclerView.ViewHolder {

  TextView name, address, email;

  public EmployeeHolder(@NonNull View itemView) {
    super(itemView);
    name = itemView.findViewById(R.id.employeeListItem_name);
    email = itemView.findViewById(R.id.employeeListItem_email);
    address = itemView.findViewById(R.id.employeeListItem_address);
  }
}
