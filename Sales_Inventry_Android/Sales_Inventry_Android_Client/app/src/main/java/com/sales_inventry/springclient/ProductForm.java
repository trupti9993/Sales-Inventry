package com.sales_inventry.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.sales_inventry.springclient.model.EmployeeDTO;
import com.sales_inventry.springclient.model.ProductDTO;
import com.sales_inventry.springclient.model.ResponseEntity;
import com.sales_inventry.springclient.reotrfit.EmployeeApi;
import com.sales_inventry.springclient.reotrfit.ProductApi;
import com.sales_inventry.springclient.reotrfit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductForm extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.create_product);

    initializeComponents();
  }

  private void initializeComponents() {
    TextInputEditText inputEditTextName = findViewById(R.id.form_textFieldName);
    TextInputEditText inputEditTextId = findViewById(R.id.form_textFieldId);
    TextInputEditText inputEditProdType = findViewById(R.id.form_textFieldProdType);
    TextInputEditText inputEditProdUnit = findViewById(R.id.form_textFieldProdUnit);


    MaterialButton buttonSave = findViewById(R.id.form_buttonSave);

     inputEditTextId.setVisibility(View.INVISIBLE);

      RetrofitService retrofitService = new RetrofitService();
      ProductApi productApi = retrofitService.getRetrofit().create(ProductApi.class);

   if(ProductListActivity.getProductId()!=-1){
       productApi.getProduct(ProductListActivity.getProductId())
               .enqueue(new Callback<ResponseEntity>() {
                   @Override
                   public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                       ProductDTO productDTO= (ProductDTO) response.body().getProductData();

                      inputEditTextName.setText(productDTO.getProdName());
                      inputEditProdType.setText(productDTO.getProdType());
                        inputEditProdUnit.setText(productDTO.getProdUnit());

                       inputEditTextId.setText(String.valueOf( productDTO.getProdId()));
                   }

                   @Override
                   public void onFailure(Call<ResponseEntity> call, Throwable t) {
                       Toast.makeText(ProductForm.this, "Failed to load Product data..!", Toast.LENGTH_SHORT).show();
                       Logger.getLogger(ProductForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
                   }
               });
       ProductListActivity.setProductId(-1);
    }else {
       inputEditTextName.setText("");
       inputEditProdType.setText("");
       inputEditProdUnit.setText("");


       inputEditTextId.setText("-1");
   }

    buttonSave.setOnClickListener(view -> {
      String name = String.valueOf(inputEditTextName.getText());
      String prodType = String.valueOf(inputEditProdType.getText());
      String prodUnit = String.valueOf(inputEditProdUnit.getText());

      Integer productId=Integer.parseInt(String.valueOf(inputEditTextId.getText()));

        if(name.trim().equals("")){
            inputEditTextName.setError("Name is Required Field..");
            return;
        }

        if(prodType.trim().equals("")){
            inputEditProdType.setError("Please Enter Product Type..! ");
            return;
        }

        if(prodUnit.trim().equals("")){
            inputEditProdUnit.setError("Please Enter Product Unit..! ");
            return;
        }



        ProductDTO product = new ProductDTO();
        product.setProdName(name);
        product.setProdId(productId);
        product.setProdType(prodType);
        product.setProdUnit(prodUnit);


        productApi.saveProduct(product)
          .enqueue(new Callback<ProductDTO>() {
            @Override
            public void onResponse(Call<ProductDTO> call, Response<ProductDTO> response)
            {


              Toast.makeText(ProductForm.this, "Product Save successful..! ", Toast.LENGTH_SHORT).show();

              Intent intent = new Intent(ProductForm.this, SaleForm.class);
              startActivity(intent);
            }

            @Override
            public void onFailure(Call<ProductDTO> call, Throwable t) {
              Toast.makeText(ProductForm.this, "Product Save failed..!", Toast.LENGTH_SHORT).show();
              Logger.getLogger(ProductForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
          });
    });
  }
}