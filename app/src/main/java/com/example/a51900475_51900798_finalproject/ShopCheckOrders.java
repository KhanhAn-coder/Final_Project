package com.example.a51900475_51900798_finalproject;

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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import shopOrders.ShopOrders;
import shopOrders.ShopOrdersViewHolder;

public class ShopCheckOrders extends AppCompatActivity {
    ImageButton imgButtonBackShopCheckOrders;
    RecyclerView rv_ShopCheckOrders;
    DatabaseReference ShopCheckOrdersRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_check_orders);

        imgButtonBackShopCheckOrders = findViewById(R.id.imgButtonBackShopCheckOrders);

        rv_ShopCheckOrders = findViewById(R.id.rv_ShopCheckOrders);
        rv_ShopCheckOrders.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        ShopCheckOrdersRef = FirebaseDatabase.getInstance().getReference().child("Orders");
        FirebaseRecyclerOptions<ShopOrders> options = new FirebaseRecyclerOptions.Builder<ShopOrders>()
                .setQuery(ShopCheckOrdersRef,ShopOrders.class)
                .build();

        FirebaseRecyclerAdapter<ShopOrders, ShopOrdersViewHolder> adapter = new FirebaseRecyclerAdapter<ShopOrders, ShopOrdersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(ShopOrdersViewHolder holder, int position, ShopOrders model) {
                holder.tvShopCheckOrdersUserName.setText("Username: "+model.getName());
                holder.tvShopCheckOrdersPhoneNumbers.setText("Phone Number: "+model.getPhone());
                holder.tvShopCheckOrdersAddress.setText("Address: "+model.getAddress());
                holder.tvShopCheckOrdersPrice.setText("Total Price: "+String.valueOf(model.getTotalAmount()));

                holder.btnShowOrderProducts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ShopCheckOrders.this, ShopOrdersProducts.class);
                        Bundle extras = new Bundle();
                        extras.putString("userPhone",model.getPhone());
                        extras.putString("shopID",LoggedUser.loggedUser.getShopID());
                        extras.putString("address",model.getAddress());
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public ShopOrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_orders_item,parent,false);
                ShopOrdersViewHolder holder = new ShopOrdersViewHolder(view);
                return holder;
            }
        };
        rv_ShopCheckOrders.setAdapter(adapter);
        adapter.startListening();

    }
}