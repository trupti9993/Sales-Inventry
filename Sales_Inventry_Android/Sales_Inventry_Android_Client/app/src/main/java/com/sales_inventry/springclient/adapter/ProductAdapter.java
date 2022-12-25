package com.sales_inventry.springclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sales_inventry.springclient.EmployeeForm;
import com.sales_inventry.springclient.EmployeeListActivity;
import com.sales_inventry.springclient.ProductForm;
import com.sales_inventry.springclient.ProductListActivity;
import com.sales_inventry.springclient.R;
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.ProductDTO;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.ProductApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductAdapter extends RecyclerView.Adapter<ProductHolder> {

  private final List<ProductDTO> productList;

  private final ProductListActivity productListActivity;

  public ProductAdapter(List<ProductDTO> productList, ProductListActivity productListActivity) {
    this.productList = productList;
    this.productListActivity=productListActivity;
  }

  @NonNull
  @Override
  public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_product_item, parent, false);
    return new ProductHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

    RetrofitService retrofitService = new RetrofitService();
    ProductApi productApi = retrofitService.getRetrofit().create(ProductApi.class);


try {
   ProductDTO product = productList.get(position);
  Integer productId=product.getProdId();
   holder.name.setText(product.getProdName());
  holder.prodType.setText(product.getProdType());
  holder.prodUnit.setText(product.getProdUnit());

  holder.updateBtn.setOnClickListener(view -> productListActivity.updateProduct(productId));
  holder.deleteBtn.setOnClickListener(view -> productListActivity.deleteProduct(productId));
} catch (Exception e){
  Toast.makeText(null, "Save failed!!!", Toast.LENGTH_SHORT).show();
  Logger.getLogger(ProductForm.class.getName()).log(Level.SEVERE, "Error occurred", e);
}


  }

  @Override
  public int getItemCount() {
    return productList.size();
  }
}
