package com.example.a51900475_51900798_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProuctDetails extends AppCompatActivity {
    ImageView imageViewDetail;
    TextView tvDetailName, tvDetailPrice, tvAmount;
    ImageButton imgButtonAdd, imgButtonRemove, imgButtonBack_Detail;
    Button btnAddtoChart;
    final int code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prouct_details);

        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvAmount = findViewById(R.id.tvAmount);

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


        btnAddtoChart = findViewById(R.id.btnAddtoChart);
        btnAddtoChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProuctDetails.this, Cart.class);
                Bundle extras = new Bundle();
                extras.putString("name",tvDetailName.getText().toString());
                extras.putInt("imageID",R.drawable.mouse_4);
                extras.putInt("price",Integer.parseInt(tvDetailPrice.getText().toString())*Integer.parseInt(tvAmount.getText().toString()));
                extras.putInt("amount",Integer.parseInt(tvAmount.getText().toString()));
                intent.putExtras(extras);
                startActivity(intent);

            }
        });
    }
}