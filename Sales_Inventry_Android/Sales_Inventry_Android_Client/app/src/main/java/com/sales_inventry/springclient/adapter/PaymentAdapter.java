package com.sales_inventry.springclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sales_inventry.springclient.EmployeeForm;

import com.sales_inventry.springclient.EmployeeListActivity;
import com.sales_inventry.springclient.PaymentForm;
import com.sales_inventry.springclient.PaymentListActivity;
import com.sales_inventry.springclient.R;
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.PaymentDTO;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.PaymentApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentHolder> {

  private final List<PaymentDTO> paymentList;

  private final PaymentListActivity paymentListActivity;

  private SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");

  public PaymentAdapter(List<PaymentDTO> paymentList, PaymentListActivity paymentListActivity) {
    this.paymentList = paymentList;
    this.paymentListActivity=paymentListActivity;
  }

  @NonNull
  @Override
  public PaymentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_payment_item, parent, false);
    return new PaymentHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull PaymentHolder holder, int position) {

    RetrofitService retrofitService = new RetrofitService();
    PaymentApi paymentApi = retrofitService.getRetrofit().create(PaymentApi.class);


try {
  PaymentDTO payment = paymentList.get(position);
  Integer paymentId=payment.getPaymentId();
   holder.id.setText(String.valueOf(paymentId));

  holder.date.setText(formatter.format(payment.getDate()));
  holder.amount.setText(String.valueOf(payment.getAmount()));

  holder.updateBtn.setOnClickListener(view -> paymentListActivity.updatePayment(paymentId));
  holder.deleteBtn.setOnClickListener(view -> paymentListActivity.deletePayment(paymentId));
} catch (Exception e){
  Toast.makeText(null, "Failed to fetch Payment data..!", Toast.LENGTH_SHORT).show();
  Logger.getLogger(PaymentForm.class.getName()).log(Level.SEVERE, "Error occurred", e);
}


  }

  @Override
  public int getItemCount() {
    return paymentList.size();
  }
}
