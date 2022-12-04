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
import com.sales_inventry.springclient.SaleListActivity;
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.SalesDTO;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;
import com.sales_inventry.springclient.reotrfit.SaleApi;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleAdapter extends RecyclerView.Adapter<SaleHolder> {

  private List<SalesDTO> saleList;

  private SaleListActivity saleListActivity;

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

    RetrofitService retrofitService = new RetrofitService();
    SaleApi saleApi = retrofitService.getRetrofit().create(SaleApi.class);


try {
  SalesDTO employee = saleList.get(position);
  Integer salesId=employee.getSalesId();
  // holder.name.setText(employee.getEmpName());
  //holder.address.setText(employee.getAddress());
  //holder.email.setText(employee.getEmail());

  holder.updateBtn.setOnClickListener(view -> saleListActivity.updateSale(salesId));
  holder.deleteBtn.setOnClickListener(view -> saleListActivity.deleteSale(salesId));
} catch (Exception e){
  Toast.makeText(null, "Save failed!!!", Toast.LENGTH_SHORT).show();
  Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, "Error occurred", e);
}


  }

  @Override
  public int getItemCount() {
    return saleList.size();
  }
}
