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
                try {
                switch (id)
                {

                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this, "Home is Clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_employee:

                    Intent intent = new Intent(MainActivity.this, EmployeeListActivity.class);

                    startActivity(intent);
                    break;

                    case R.id.nav_party:
                        Toast.makeText(MainActivity.this, "Party is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_product:
                        Toast.makeText(MainActivity.this, "Product is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_purchase:
                        Toast.makeText(MainActivity.this, "Purchase is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_sale:
                        Toast.makeText(MainActivity.this, "Sale is Clicked",Toast.LENGTH_SHORT).show();break;

                    case R.id.nav_payment:
                        Toast.makeText(MainActivity.this, "Payment is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_receipt:
                        Toast.makeText(MainActivity.this, "Receipt is clicked",Toast.LENGTH_SHORT).show();break;

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