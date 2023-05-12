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
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        imgButtonBackUserOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserOrders.this, ProfileUser.class);
                startActivity(intent);
            }
        });

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
                holder.tvUserOdersPhoneNumber.setText("Phone number: "+model.getPhoneNumber());
                holder.tvUserOrsersAddress.setText("Address: "+model.getAddress());
                holder.tvUserOrdersTotalPrice.setText("Price: "+String.valueOf(model.getTotalPrice()));
                holder.tvUserOrdersStatus.setText(model.getStatus());
                holder.btnOrderReceived.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (holder.tvUserOrdersStatus.getText().toString().equals("Accepted")){
                            Toast.makeText(UserOrders.this, "Your order is accepted", Toast.LENGTH_SHORT).show();
                        }else if (holder.tvUserOrdersStatus.getText().toString().equals("On Delivery")){
                            Toast.makeText(UserOrders.this, "Your order is delivering to you", Toast.LENGTH_SHORT).show();
                        } else if (holder.tvUserOrdersStatus.getText().toString().equals("Delivered")) {
                            FirebaseDatabase.getInstance().getReference()
                                            .child("Orders Update")
                                                    .child(LoggedUser.loggedUser.getPhone())
                                                            .child(model.getShopID())
                                                                    .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            FirebaseDatabase.getInstance().getReference()
                                                    .child("Cart List")
                                                    .child("Admin View")
                                                    .child(LoggedUser.loggedUser.getPhone())
                                                    .child("Products")
                                                    .orderByChild("shopID").equalTo(holder.tvUserOrdersShopID.getText().toString())
                                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            for (DataSnapshot childSnapshot : snapshot.getChildren()){
                                                                childSnapshot.getRef().removeValue();
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });
                                            Intent intent = new Intent(UserOrders.this,ProfileUser.class);
                                            startActivity(intent);
                                            Toast.makeText(UserOrders.this, "Thanks for your Purchase", Toast.LENGTH_SHORT).show();


                                        }
                                    });
                        }
                    }
                });
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