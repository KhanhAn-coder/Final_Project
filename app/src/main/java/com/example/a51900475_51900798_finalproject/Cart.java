package com.example.a51900475_51900798_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    Button btnNext, btnCalculate;
    TextView tvTotalPrice;
    ImageView imageViewCartProduct;
    DatabaseReference cartListRef;
    ImageButton imgButtonBackCart;
    int totalPrice = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tvTotalPrice = findViewById(R.id.tvTotalPrice);


        rvCart = findViewById(R.id.rvCart);
        rvCart.setHasFixedSize(true);
        rvCart.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

//        imageViewCartProduct = findViewById(R.id.imageViewCartProduct);


        imgButtonBackCart = findViewById(R.id.imgButtonBackCart);
        imgButtonBackCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Cart.this,HomePage.class);
               startActivity(intent);
            }
        });

        btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTotalPrice.setText(String.valueOf(totalPrice)+"đ");
            }
        });

        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this,Order.class);
                intent.putExtra("totalPrice",totalPrice);
                startActivity(intent);
                finish();
            }
        });

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
                holder.tvCartPrice.setText(String.valueOf(model.getPrice())+"đ");
                Picasso.get().load(model.getSourceID()).into(holder.imageViewCartProduct);

                int oneTypeProductPrice = (Integer.valueOf(model.getPrice())) * Integer.valueOf(model.getQuantity());
                totalPrice = totalPrice + oneTypeProductPrice;

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CharSequence options[] = new CharSequence[]{
                          "Edit", "Remove"
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(Cart.this);
                        builder.setTitle("Cart Options:");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               if (i==0){
                                   Intent intent = new Intent(Cart.this,ProuctDetails.class);
                                   Bundle extras = new Bundle();
                                   extras.putString("productID", model.getProductID());
                                   extras.putString("sourceID",model.getSourceID());
                                   extras.putString("shopID",model.getShopID());
                                   extras.putInt("Limit",model.getLimit());
                                   extras.putInt("Sales",model.getSales());
                                   extras.putString("type", model.getType());
                                   intent.putExtras(extras);
                                   startActivity(intent);
                               }
                               if (i==1){
                                   cartListRef.child("User View")
                                           .child(LoggedUser.loggedUser.getPhone())
                                           .child("Products")
                                           .child(model.getProductID())
                                           .removeValue()
                                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                                               @Override
                                               public void onComplete(Task<Void> task) {
                                                   Toast.makeText(Cart.this, "Removed Successful", Toast.LENGTH_SHORT).show();
                                                   Intent intent = new Intent(Cart.this,HomePage.class);
                                                   startActivity(intent);
                                               }
                                           });
                               }
                            }

                        });
                        builder.show();
                    }
                });

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