package com.example.a51900475_51900798_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShopOrdersProducts extends AppCompatActivity {
    ImageButton imgbtnShopOrdersProductsBackCart;
    RecyclerView rvShopOrdersProducts;
    Button btnShopOrdersProductsCalculate, btnShopOrdersProductsNext;
    DatabaseReference ShopOrdersProductsRef;
    String userPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_orders_products);

        imgbtnShopOrdersProductsBackCart = findViewById(R.id.imgbtnShopOrdersProductsBackCart);
        rvShopOrdersProducts = findViewById(R.id.rvShopOrdersProducts);
        rvShopOrdersProducts.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        userPhone = getIntent().getStringExtra("userPhone");
        ShopOrdersProductsRef = FirebaseDatabase.getInstance().getReference()
                .child("Cart List")
                .child("Admin View");

        //7:32 vid 31

    }
}