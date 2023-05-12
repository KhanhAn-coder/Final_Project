package com.example.a51900475_51900798_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cartproduct.CartProductViewHolder;
import userOrders.UserOrdersObject;
import userOrders.UserOrdersViewHolder;

public class UserOrders extends AppCompatActivity {
    ImageButton imgButtonBackUserOrders;
    RecyclerView rv_UserOrders;
    DatabaseReference userordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_orders);

        imgButtonBackUserOrders = findViewById(R.id.imgButtonBackUserOrders);

        rv_UserOrders = findViewById(R.id.rv_UserOrders);
        rv_UserOrders.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        userordersRef = FirebaseDatabase.getInstance().getReference().child("Orders Update").child(LoggedUser.loggedUser.getPhone());

        FirebaseRecyclerOptions<UserOrdersObject> options = new FirebaseRecyclerOptions.Builder<UserOrdersObject>()
                .setQuery(userordersRef,UserOrdersObject.class)
                .build();
        FirebaseRecyclerAdapter<UserOrdersObject, UserOrdersViewHolder> adapter = new FirebaseRecyclerAdapter<UserOrdersObject, UserOrdersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UserOrdersViewHolder holder, int position, @NonNull UserOrdersObject model) {
                holder.tvUserOrdersShopID.setText(model.getShopID());
                holder.tvUserOdersPhoneNumber.setText(model.getPhoneNumber());
                holder.tvUserOrsersAddress.setText(model.getAddress());
                holder.tvUserOrdersTotalPrice.setText(String.valueOf(model.getTotalPrice()));
                holder.tvUserOrdersStatus.setText(model.getStatus());
            }

            @NonNull
            @Override
            public UserOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_orders_item,parent,false);
                UserOrdersViewHolder holder = new UserOrdersViewHolder(view);
                return holder;
            }
        };
        rv_UserOrders.setAdapter(adapter);
        adapter.startListening();
    }
}