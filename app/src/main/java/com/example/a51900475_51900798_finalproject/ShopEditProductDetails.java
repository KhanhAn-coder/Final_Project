package com.example.a51900475_51900798_finalproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class ShopEditProductDetails extends AppCompatActivity {
    private String category, description, price, name_Product, imageLink , productID;
    EditText ProductName, ProductDescription, ProductPrice;
    private Uri imageUri;
    ImageView ProductImage;
    Intent intent = getIntent();
    private StorageReference ProductImageRef;
    private DatabaseReference ProductRef;
    private Button btn_update;

    String prodName = intent.getStringExtra("productID");

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null){

                        imageUri = result.getData().getData();
                        ProductImage.setImageURI(imageUri);

                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_edit_product_details);
        btn_update = findViewById(R.id.btn_update);
        ProductImage = findViewById(R.id.shop_ProductImage);
        ProductDescription = findViewById(R.id.shop_ProductDescription);
        ProductName = findViewById(R.id.shop_ProductName);
        ProductPrice = findViewById(R.id.shop_ProductPrice);




        showCurrentProductInfo(ProductName, ProductDescription, ProductPrice);



    }



    private void showCurrentProductInfo(EditText productName, EditText productDescription, EditText productPrice) {
        DatabaseReference prodRef = FirebaseDatabase.getInstance().getReference().child("Products").child(prodName);
        prodRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    if (snapshot.child(prodName).exists()){
                        String prodName = snapshot.child("").getValue().toString();
                        String prodDescription = snapshot.child("Email").getValue().toString();
                        String prodPrice = snapshot.child("Phone").getValue().toString();


                        productName.setText(prodName);
                        productDescription.setText(prodDescription);
                        productPrice.setText(prodPrice);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }





}