package com.example.a51900475_51900798_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import cartproduct.CartProduct;
import cartproduct.CartProductViewHolder;

public class ShopOrdersProducts extends AppCompatActivity {
    ImageButton imgbtnShopOrdersProductsBackCart;
    RecyclerView rvShopOrdersProducts;
    Button btnShopOrdersProductsCalculate, btnShopOrdersProductsNext;
    DatabaseReference ShopOrdersProductsRef;
    String userPhone, shopID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_orders_products);

        imgbtnShopOrdersProductsBackCart = findViewById(R.id.imgbtnShopOrdersProductsBackCart);
        rvShopOrdersProducts = findViewById(R.id.rvShopOrdersProducts);
        rvShopOrdersProducts.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        userPhone = getIntent().getExtras().getString("userPhone");
        shopID = getIntent().getExtras().getString("shopID");

        ShopOrdersProductsRef = FirebaseDatabase.getInstance().getReference()
                .child("Cart List")
                .child("Admin View");

        Query query = ShopOrdersProductsRef.child(userPhone).child("Products").orderByChild("shopID").equalTo(shopID);

        FirebaseRecyclerOptions<CartProduct> options = new FirebaseRecyclerOptions.Builder<CartProduct>()
                .setQuery(query,CartProduct.class)
                .build();
        FirebaseRecyclerAdapter<CartProduct, CartProductViewHolder> adapter = new FirebaseRecyclerAdapter<CartProduct, CartProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(CartProductViewHolder holder, int position, CartProduct model) {
                holder.tvCartName.setText(model.getProductName());
                holder.tvCartAmount.setText(String.valueOf(model.getQuantity()));
                holder.tvCartPrice.setText(String.valueOf(model.getPrice())+"đ");
                Picasso.get().load(model.getSourceID()).into(holder.imageViewCartProduct);
            }

            @Override
            public CartProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_item,parent,false);
                CartProductViewHolder holder = new CartProductViewHolder(view);
                return holder;
            }
        };
        rvShopOrdersProducts.setAdapter(adapter);
        adapter.startListening();

    }
}