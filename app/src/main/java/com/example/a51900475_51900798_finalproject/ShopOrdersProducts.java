package com.example.a51900475_51900798_finalproject;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import cartproduct.CartProduct;
import cartproduct.CartProductViewHolder;

public class ShopOrdersProducts extends AppCompatActivity {
    ImageButton imgbtnShopOrdersProductsBackCart;
    RecyclerView rvShopOrdersProducts;
    Button btnShopOrdersProductsCalculate, btnShopOrdersProductsNext;
    DatabaseReference ShopOrdersProductsRef;
    String userPhone, shopID, address;
    TextView tvShopOrdersProductsTotalPrice, tvStatusCheck;
    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_orders_products);



        tvStatusCheck = findViewById(R.id.tvStatusCheck);
        tvStatusCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] statusList = {"Accepted","On Delivery","Delivered"};
                int CheckedItem = 1;
                AlertDialog.Builder builder = new AlertDialog.Builder(ShopOrdersProducts.this);
                builder.setTitle("Choose Status");
                builder.setSingleChoiceItems(statusList, CheckedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                tvStatusCheck.setText("Accepted");
                                break;
                            case 1:
                                tvStatusCheck.setText("On Delivery");
                                break;
                            case 2:
                                tvStatusCheck.setText("Delivered");
                                break;
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        tvShopOrdersProductsTotalPrice = findViewById(R.id.tvShopOrdersProductsTotalPrice);

        btnShopOrdersProductsNext = findViewById(R.id.btnShopOrdersProductsNext);
        btnShopOrdersProductsNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateOrders();
            }
        });

        btnShopOrdersProductsCalculate = findViewById(R.id.btnShopOrdersProductsCalculate);
        btnShopOrdersProductsCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvShopOrdersProductsTotalPrice.setText(String.valueOf(totalPrice));
            }
        });

        imgbtnShopOrdersProductsBackCart = findViewById(R.id.imgbtnShopOrdersProductsBackCart);

        imgbtnShopOrdersProductsBackCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopOrdersProducts.this, ShopCheckOrders.class);
                startActivity(intent);
            }
        });
        rvShopOrdersProducts = findViewById(R.id.rvShopOrdersProducts);
        rvShopOrdersProducts.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        userPhone = getIntent().getExtras().getString("userPhone");
        shopID = getIntent().getExtras().getString("shopID");
        address = getIntent().getExtras().getString("address");

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

                int oneTypeProductPrice = (Integer.valueOf(model.getPrice())) * Integer.valueOf(model.getQuantity());
                totalPrice = totalPrice + oneTypeProductPrice;
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

    private void updateOrders() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference OrdersUpdateRef = FirebaseDatabase.getInstance().getReference().child("Orders Update");
        final HashMap<String,Object> ordersupdateMap = new HashMap<>();
        ordersupdateMap.put("phoneNumber",userPhone);
        ordersupdateMap.put("shopID",shopID);
        ordersupdateMap.put("totalPrice",totalPrice);
        ordersupdateMap.put("address",address);
        ordersupdateMap.put("status",tvStatusCheck.getText().toString());

        OrdersUpdateRef.child(userPhone).child(shopID)
                .updateChildren(ordersupdateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                Intent intent = new Intent(ShopOrdersProducts.this,UserShop.class);
                intent.putExtra("status","success");
                startActivity(intent);
                Toast.makeText(ShopOrdersProducts.this, "Update order successfully", Toast.LENGTH_SHORT).show();

            }
        });

    }
}