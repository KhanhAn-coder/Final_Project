package com.example.a51900475_51900798_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

import product.Product;
import product.ProductAdapter;

public class ListMouseProduct extends AppCompatActivity {
    RecyclerView rv_mouseProduct;
    ProductAdapter mouseProductAdapter;
    ArrayList<Product> listMouseProduct = new ArrayList<>();
    ImageButton imgButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mouse_product);
        rv_mouseProduct = findViewById(R.id.rv_mouseProduct);

//        listMouseProduct.add(new Product("Chuột máy tính 1",390000,4.6,1.2,R.drawable.mouse_1, "mouse"));
//        listMouseProduct.add(new Product("Chuột máy tính 2",279000,4.5,2.5,R.drawable.mouse_2,"mouse"));
//        listMouseProduct.add(new Product("Chuột máy tính 3",599000,4.8,1.6,R.drawable.mouse_3,"mouse"));
//        listMouseProduct.add(new Product("Chuột máy tính 4",259000,5.0,1.9,R.drawable.mouse_4,"mouse"));
//        listMouseProduct.add(new Product("Chuột máy tính 5",490000,4.3,1.8,R.drawable.mouse_5,"mouse"));
//        listMouseProduct.add(new Product("Chuột máy tính 6",649000,4.5,2.7,R.drawable.mouse_6,"mouse"));

        mouseProductAdapter = new ProductAdapter();
        mouseProductAdapter.setData(listMouseProduct);

        rv_mouseProduct.setLayoutManager(new GridLayoutManager(this,2));
        rv_mouseProduct.setAdapter(mouseProductAdapter);

        imgButtonBack = findViewById(R.id.imgButtonBack);
        imgButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListMouseProduct.this, HomePage.class);
                intent.putExtra("code","success");
                startActivity(intent);
            }
        });


    }
}