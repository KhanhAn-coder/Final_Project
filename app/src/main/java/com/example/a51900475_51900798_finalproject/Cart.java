package com.example.a51900475_51900798_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cartproduct.CartProduct;
import cartproduct.CartProductViewHolder;

public class Cart extends AppCompatActivity {
    RecyclerView rvCart;
    ArrayList<CartProduct> listcartProduct = new ArrayList<>();
    Button btnNext;
    TextView tvTotalPrice;
    ImageView imageViewCartProduct;
    DatabaseReference cartListRef;
    ImageButton imgButtonBackCart;
    Query query;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        rvCart = findViewById(R.id.rvCart);
        rvCart.setHasFixedSize(true);
        rvCart.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        imageViewCartProduct = findViewById(R.id.imageViewCartProduct);


        imgButtonBackCart = findViewById(R.id.imgButtonBackCart);
        imgButtonBackCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Cart.this,HomePage.class);
               startActivity(intent);
            }
        });


        btnNext = findViewById(R.id.btnNext);

        cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<CartProduct> options = new FirebaseRecyclerOptions.Builder<CartProduct>()
                .setQuery(cartListRef.child("User View").child(LoggedUser.loggedUser.getPhone())
                        .child("Products"), CartProduct.class)
                .build();

        FirebaseRecyclerAdapter<CartProduct, CartProductViewHolder> adapter = new FirebaseRecyclerAdapter<CartProduct, CartProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(CartProductViewHolder holder, int position, CartProduct model) {
                holder.tvCartName.setText(model.getProductName());
                holder.tvCartAmount.setText(String.valueOf(model.getQuantity()));
                holder.tvCartPrice.setText(String.valueOf(model.getPrice()));
                Picasso.get().load(model.getSourceID()).into(holder.imageViewCartProduct);

            }

            @Override
            public CartProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_item,parent,false);
                CartProductViewHolder holder = new CartProductViewHolder(view);
                return holder;
            }
        };
        rvCart.setAdapter(adapter);
        adapter.startListening();
    }



}