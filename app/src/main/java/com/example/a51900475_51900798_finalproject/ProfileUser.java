package com.example.a51900475_51900798_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileUser extends AppCompatActivity {
    ArrayList<String> listUserExtensions = new ArrayList<>();
    ArrayList<Products> listProducts = new ArrayList<>();
    RecyclerView profileUserRV;
    RecyclerView profileUserHotSalesRV;
    ProfileUserExtensionsAdapter profileUserExtensionsAdapter;
    ProductsAdapter productsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);




        profileUserHotSalesRV = findViewById(R.id.profileUserHotSalesRV);

        listProducts.add(new Products("Giày thể thao",299000,4,1200));
        listProducts.add(new Products("Giày thể thao",299000,4,1200));
        listProducts.add(new Products("Giày thể thao",299000,4,1200));

        productsAdapter = new ProductsAdapter(listProducts);
        profileUserHotSalesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        profileUserHotSalesRV.setAdapter(productsAdapter);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}