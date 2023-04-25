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

import product.Product;
import product.ProductAdapter;

public class ProfileUser extends AppCompatActivity {
    ArrayList<String> listUserExtensions = new ArrayList<>();
    ArrayList<Product> listProducts = new ArrayList<>();
    RecyclerView profileUserRV;
    RecyclerView profileUserHotSalesRV;
    ProfileUserExtensionsAdapter profileUserExtensionsAdapter;
    //ProductsAdapter productsAdapter;
    ProductAdapter productAdapter;
    TextView tvHotSales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        profileUserRV = findViewById(R.id.profileUserRV);
        tvHotSales = findViewById(R.id.tvHotSales);


        listUserExtensions.add("Đã thích");
        listUserExtensions.add("Đánh giá của tôi");
        listUserExtensions.add("Thiết lập tài khoản");

        profileUserExtensionsAdapter = new ProfileUserExtensionsAdapter(listUserExtensions);

        profileUserRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        profileUserRV.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        profileUserRV.setAdapter(profileUserExtensionsAdapter);




        profileUserHotSalesRV = findViewById(R.id.profileUserHotSalesRV);

        listProducts.add(new Product("Giày",299000,4.2,1.2,R.drawable.sneaker1));
        listProducts.add(new Product("Bàn phím",599000,4.6,1.2,R.drawable.keyboard3));
        listProducts.add(new Product("Quần áo",150000,5.0,2.5,R.drawable.shirt));
        productAdapter = new ProductAdapter();

        productAdapter.setData(listProducts);

        profileUserHotSalesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        profileUserHotSalesRV.setAdapter(productAdapter);





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