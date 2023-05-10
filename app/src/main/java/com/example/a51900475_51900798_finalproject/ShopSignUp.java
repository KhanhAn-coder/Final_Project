package com.example.a51900475_51900798_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ShopSignUp extends AppCompatActivity {
    EditText shopSignUp_ShopName, shopSignUp_ShopAddress, shopSignUp_ShopEmail, shopSignUp_ShopPhone;
    Button shopSignUp_btnSubmit;
    private String shopName, shopEmail, shopPhone, shopAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_sign_up);

        shopSignUp_ShopName = findViewById(R.id.shopSignUp_ShopName);
        shopSignUp_ShopAddress = findViewById(R.id.shopSignUp_ShopAddress);
        shopSignUp_ShopEmail = findViewById(R.id.shopSignUp_ShopEmail);
        shopSignUp_ShopPhone = findViewById(R.id.shopSignUp_ShopPhone);
        shopSignUp_btnSubmit = findViewById(R.id.shopSignUp_btnSubmit);

        shopSignUp_btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddShopDetails();
            }
        });
    }

    private void AddShopDetails() {
        shopName = shopSignUp_ShopName.getText().toString();
        shopAddress = shopSignUp_ShopAddress.getText().toString();
        shopPhone = shopSignUp_ShopPhone.getText().toString();
        shopEmail = shopSignUp_ShopEmail.getText().toString();


        if (TextUtils.isEmpty(shopName)){
            Toast.makeText(this, "Vui lòng nhập tên cửa hàng", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(shopAddress)){
            Toast.makeText(this, "Vui lòng nhập địa chỉ cửa hàng", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(shopEmail)){
            Toast.makeText(this, "Vui lòng nhập email liên lạc cửa hàng", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(shopPhone)){
            Toast.makeText(this, "Vui lòng nhập số điện thoại liên lạc cửa hàng", Toast.LENGTH_SHORT).show();
        }else
        {
            addShopToDatabase();
        }
    }

    private void addShopToDatabase() {
        final DatabaseReference shopRef;
        shopRef = FirebaseDatabase.getInstance().getReference();

        final DatabaseReference userRef;
        userRef = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> shopMap = new HashMap<>();
        shopMap.put("ShopName", shopName);
        shopMap.put("ShopAddress", shopAddress);
        shopMap.put("ShopEmail", shopEmail);
        shopMap.put("shopPhone", shopPhone);
        shopMap.put("Owner Phone", LoggedUser.loggedUser.getPhone());

        shopRef.child("Shop").child(shopName).updateChildren(shopMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ShopSignUp.this, "Put successful", Toast.LENGTH_SHORT).show();

                }
            }
        });
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("ShopID", shopName);
        userRef.child("Users").child(LoggedUser.loggedUser.getPhone()).updateChildren(userMap);
        LoggedUser.loggedUser.setShopID(shopName);
        Intent intent = new Intent(ShopSignUp.this, ProfileUser.class);
        startActivity(intent);
    }
}