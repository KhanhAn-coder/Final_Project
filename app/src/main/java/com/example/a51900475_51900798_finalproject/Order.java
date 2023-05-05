package com.example.a51900475_51900798_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Order extends AppCompatActivity {
    EditText edtName,edtHomeAddress, edtPhoneNumber;
    Button btnConfirm;
    int totalAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        edtName = findViewById(R.id.edtName);
        edtHomeAddress = findViewById(R.id.edtHomeAddress);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);

        totalAmount = getIntent().getIntExtra("totalPrice",0);
        Toast.makeText(this, String.valueOf(totalAmount), Toast.LENGTH_SHORT).show();

        btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtName.getText().toString().equals("")){
                    Toast.makeText(Order.this, "PLease enter your name", Toast.LENGTH_SHORT).show();
                }
                if(edtHomeAddress.getText().toString().equals("")){
                    Toast.makeText(Order.this, "Please enter your home address", Toast.LENGTH_SHORT).show();
                }
                if(edtPhoneNumber.getText().toString().equals("")){
                    Toast.makeText(Order.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                }else {
                    final String saveCurrentDate, saveCurrentTime;
                    Calendar calForDate = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                    saveCurrentDate = currentDate.format(calForDate.getTime());

                    SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                    saveCurrentTime = currentTime.format(calForDate.getTime());

                    final DatabaseReference OrdersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(LoggedUser.loggedUser.getPhone());

                    HashMap<String,Object> ordersMap = new HashMap<>();
                    ordersMap.put("totalAmount",totalAmount);
                    ordersMap.put("name",edtName.getText().toString());
                    ordersMap.put("phone",edtPhoneNumber.getText().toString());
                    ordersMap.put("address",edtHomeAddress.getText().toString());
                    ordersMap.put("date",saveCurrentDate);
                    ordersMap.put("time",saveCurrentTime);

                    OrdersRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if(task.isSuccessful()){
                                FirebaseDatabase.getInstance().getReference()
                                        .child("Cart List")
                                        .child("User View")
                                        .child(LoggedUser.loggedUser.getPhone())
                                        .removeValue()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(Order.this, "Your Order has been successfully ordered", Toast.LENGTH_SHORT).show();
                                                    Intent intent =  new Intent(Order.this,HomePage.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                }
                                            }
                                        });
                            }
                        }
                    });
                }
            }
        });
    }
}