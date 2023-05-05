package com.example.a51900475_51900798_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import product.Productss;

public class ProuctDetails extends AppCompatActivity {
    ImageView imageViewDetail;
    TextView tvDetailName, tvDetailPrice, tvAmount, DetailRating, tvProductName, tvDescription;
    ImageButton imgButtonAdd, imgButtonRemove, imgButtonBack_Detail;
    Button btnAddtoChart;
    final int code = 0;
    String productID = "";
    String type;
    String sourceID;
    int randomKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prouct_details);

        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvAmount = findViewById(R.id.tvAmount);
        DetailRating = findViewById(R.id.DetailRating);
        tvProductName = findViewById(R.id.tvProductName);
        imageViewDetail = findViewById(R.id.imageViewDetail);
        tvDescription = findViewById(R.id.tvDescription);

        //productID,type nhận về từ item trong listProduct
        productID = getIntent().getExtras().getString("productID");
        type = getIntent().getExtras().getString("type");
        sourceID = getIntent().getExtras().getString("sourceID");
        tvProductName.setText(type);

        getProductDetails(productID);

        imgButtonAdd = findViewById(R.id.imgButtonAdd);
        imgButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = tvAmount.getText().toString();
                int count = Integer.parseInt(amount)+1;
                tvAmount.setText(String.valueOf(count));
            }
        });

        imgButtonRemove = findViewById(R.id.imgButtonRemove);
        imgButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = tvAmount.getText().toString();
                int count = Integer.parseInt(amount)-1;
                tvAmount.setText(String.valueOf(count));
            }
        });
        imgButtonBack_Detail = findViewById(R.id.imgButtonBack_Detail);
        imgButtonBack_Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("sales_product")){
                    Intent intent = new Intent(ProuctDetails.this,HomePage.class);
                    intent.putExtra("code","success");
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ProuctDetails.this,ListProduct.class);
                    intent.putExtra("type",type);
                    startActivity(intent);
                }

            }
        });


        btnAddtoChart = findViewById(R.id.btnAddtoChart);
        btnAddtoChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addingToCartList();

            }
        });
    }



    private void addingToCartList() {

        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String, Object> cartMap = new HashMap<>();
        //cartMap.put("cartID",cartID);
        cartMap.put("sourceID",sourceID);
        cartMap.put("productID",productID);
        cartMap.put("productName",tvDetailName.getText().toString());
        cartMap.put("price",Integer.parseInt(tvDetailPrice.getText().toString()));
        cartMap.put("date",saveCurrentDate);
        cartMap.put("time",saveCurrentTime);
        cartMap.put("quantity",Integer.parseInt(tvAmount.getText().toString()));

        cartListRef.child("User View").child(LoggedUser.loggedUser.getPhone()).child("Products").child(productID)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()){
                    cartListRef.child("Admin View").child(LoggedUser.loggedUser.getPhone()).child("Products").child(productID)
                            .updateChildren(cartMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(ProuctDetails.this, "Added to Cart List", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(ProuctDetails.this,Cart.class);
                                        Bundle extras = new Bundle();
                                        extras.putString("from","productdetails");
                                        extras.putString("productID",productID);
                                        intent.putExtras(extras);
                                        startActivity(intent);
                                    }
                                }
                            });
                }
            }
        });
    }

    private void getProductDetails(String productID) {
        DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference().child("Products");
        RootRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Productss productss = snapshot.getValue(Productss.class);
                    tvDetailName.setText(productss.getTitle());
                    tvDetailPrice.setText(String.valueOf(productss.getPrice()));
                    DetailRating.setText(String.valueOf(productss.getRating()));
                    tvDescription.setText(productss.getDescription());
                    Picasso.get().load(productss.getSourceID()).into(imageViewDetail);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}