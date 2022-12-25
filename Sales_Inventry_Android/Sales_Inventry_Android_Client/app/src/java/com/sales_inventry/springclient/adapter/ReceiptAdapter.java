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
import com.sales_inventry.springclient.ReceiptForm;
import com.sales_inventry.springclient.ReceiptListActivity;
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.ReceiptDTO;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.ReceiptApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptHolder> {

  private final List<ReceiptDTO> receiptList;

  private final ReceiptListActivity receiptListActivity;

  public ReceiptAdapter(List<ReceiptDTO> receiptList, ReceiptListActivity receiptListActivity) {
    this.receiptList = receiptList;
    this.receiptListActivity=receiptListActivity;
  }

  @NonNull
  @Override
  public ReceiptHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_receipt_item, parent, false);
    return new ReceiptHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ReceiptHolder holder, int position) {

    RetrofitService retrofitService = new RetrofitService();
    ReceiptApi receiptApi = retrofitService.getRetrofit().create(ReceiptApi.class);


try {
   ReceiptDTO receipt = receiptList.get(position);
  Integer receiptId=receipt.getReceiptId();
 //  holder.name.setText(employee.getEmpName());
  //holder.address.setText(employee.getAddress());
  //holder.email.setText(employee.getEmail());

  holder.updateBtn.setOnClickListener(view -> receiptListActivity.updateReceipt(receiptId));
  holder.deleteBtn.setOnClickListener(view -> receiptListActivity.deleteReceipt(receiptId));
} catch (Exception e){
  Toast.makeText(null, "Save failed!!!", Toast.LENGTH_SHORT).show();
  Logger.getLogger(ReceiptForm.class.getName()).log(Level.SEVERE, "Error occurred", e);
}


  }

  @Override
  public int getItemCount() {
    return receiptList.size();
  }
}
