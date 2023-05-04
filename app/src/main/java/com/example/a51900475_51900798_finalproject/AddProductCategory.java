package com.example.a51900475_51900798_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AddProductCategory extends AppCompatActivity implements View.OnClickListener {
    private ImageView mouse_category, keyboard_category, shoes_category, clothes_category, watch_category, camera_category, hotSales_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_category);
        mouse_category = findViewById(R.id.mouse_category);
        keyboard_category = findViewById(R.id.keyboard_category);
        shoes_category = findViewById(R.id.shoes_category);
        clothes_category = findViewById(R.id.clothes_category);
        watch_category = findViewById(R.id.watch_category);
        camera_category = findViewById(R.id.camera_category);
        hotSales_category = findViewById(R.id.hotSales_category);



        mouse_category.setOnClickListener(this);
        keyboard_category.setOnClickListener(this);
        shoes_category.setOnClickListener(this);
        clothes_category.setOnClickListener(this);
        watch_category.setOnClickListener(this);
        camera_category.setOnClickListener(this);
        hotSales_category.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.equals(mouse_category)){
            Intent intent = new Intent(AddProductCategory.this, AddProduct.class);
            intent.putExtra("Category","mouse_product");
            startActivity(intent);
        }
        else if (view.equals(keyboard_category)){
            Intent intent = new Intent(AddProductCategory.this, AddProduct.class);
            intent.putExtra("Category","keyboard_product");
            startActivity(intent);
        }
        else if (view.equals(shoes_category)){
            Intent intent = new Intent(AddProductCategory.this, AddProduct.class);
            intent.putExtra("Category","shoes_product");
            startActivity(intent);
        }
        else if (view.equals(clothes_category)){
            Intent intent = new Intent(AddProductCategory.this, AddProduct.class);
            intent.putExtra("Category","clothes_product");
            startActivity(intent);
        }
        else if (view.equals(watch_category)){
            Intent intent = new Intent(AddProductCategory.this, AddProduct.class);
            intent.putExtra("Category","watch_product");
            startActivity(intent);
        }
        else if (view.equals(camera_category)){
            Intent intent = new Intent(AddProductCategory.this, AddProduct.class);
            intent.putExtra("Category","camera_product");
            startActivity(intent);
        }
        else if (view.equals(hotSales_category)){
            Intent intent = new Intent(AddProductCategory.this, AddProduct.class);
            intent.putExtra("Category","sales_product");
            startActivity(intent);
        }

    }
}