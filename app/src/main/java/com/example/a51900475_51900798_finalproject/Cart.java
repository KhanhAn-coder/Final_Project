package com.example.a51900475_51900798_finalproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import cartproduct.CartAdapter;
import cartproduct.CartProduct;

public class Cart extends AppCompatActivity {
    RecyclerView rvCart;
    ArrayList<CartProduct> listcartProduct = new ArrayList<>();
    CartAdapter cartAdapter;
    ImageButton imgButtonBackCart;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvCart = findViewById(R.id.rvCart);

        listcartProduct.add(new CartProduct(R.drawable.mouse_4,"Chuột bàn phím 4",100000,1));
        cartAdapter = new CartAdapter();
        cartAdapter.setData(listcartProduct);
        rvCart.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvCart.setAdapter(cartAdapter);
        getDataFromProductDetail();

        imgButtonBackCart = findViewById(R.id.imgButtonBackCart);
        imgButtonBackCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this, ProuctDetails.class);
                intent.putExtra("code","success");
                startActivity(intent);
            }
        });
    }

    private void getDataFromProductDetail() {
        Intent intent = getIntent();
        Bundle extras =  intent.getExtras();
        listcartProduct.add(new CartProduct(extras.getInt("imageID"),extras.getString("name"),extras.getInt("price"),extras.getInt("amount")));
        cartAdapter = new CartAdapter();
        cartAdapter.setData(listcartProduct);
        rvCart.setAdapter(cartAdapter);
    }


}