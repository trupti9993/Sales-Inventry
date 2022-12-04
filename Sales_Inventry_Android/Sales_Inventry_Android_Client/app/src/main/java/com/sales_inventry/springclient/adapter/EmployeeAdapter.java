package com.sales_inventry.springclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sales_inventry.springclient.EmployeeForm;
import com.sales_inventry.springclient.EmployeeListActivity;
import com.sales_inventry.springclient.R;
import com.sales_inventry.springclient.model.EmployeeDTO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeHolder> {

  private List<EmployeeDTO> employeeList;

  public EmployeeAdapter(List<EmployeeDTO> employeeList) {
    this.employeeList = employeeList;
  }

  @NonNull
  @Override
  public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_employee_item, parent, false);
    return new EmployeeHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {


try {
   EmployeeDTO employee = employeeList.get(position);

   holder.name.setText(employee.getEmpName());
  holder.address.setText(employee.getAddress());
  holder.email.setText(employee.getEmail());
} catch (Exception e){
  Toast.makeText(null, "Save failed!!!", Toast.LENGTH_SHORT).show();
  Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, "Error occurred", e);
}


  }

  @Override
  public int getItemCount() {
    return employeeList.size();
  }
}