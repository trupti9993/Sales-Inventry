package com.sales_inventry.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {

                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent=null;
                try {
                switch (id)
                {

                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this, "Home is Clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_employee:
                     intent = new Intent(MainActivity.this, EmployeeListActivity.class);
                    break;
                    case R.id.nav_party:
                         intent = new Intent(MainActivity.this, PartyListActivity.class);
                        break;
                    case R.id.nav_product:
                        intent = new Intent(MainActivity.this, ProductListActivity.class);
                        break;
                    case R.id.nav_purchase:
                        intent = new Intent(MainActivity.this, PurchaseListActivity.class);
                        break;
                    case R.id.nav_sale:
                        intent = new Intent(MainActivity.this, SaleListActivity.class);
                        break;

                    case R.id.nav_payment:
                        intent = new Intent(MainActivity.this, PaymentListActivity.class);
                        break;
                    case R.id.nav_receipt:
                        intent = new Intent(MainActivity.this, ReceiptListActivity.class);
                        break;

                }
                if(intent!=null){
                    startActivity(intent);
                }

                    return true;
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.toString(),Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });


    }
}