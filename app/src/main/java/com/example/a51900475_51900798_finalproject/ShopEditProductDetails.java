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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class ShopEditProductDetails extends AppCompatActivity {
    private String category, description, price, name_Product, imageLink , productID;
    EditText ProductName, ProductDescription, ProductPrice;
    private Uri imageUri;
    ImageView ProductImage;

    private StorageReference ProductImageRef;
    private DatabaseReference ProductRef;
    private Button btn_update;
    private String prodName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_edit_product_details);
        btn_update = findViewById(R.id.btn_update);
        ProductImage = findViewById(R.id.shop_ProductImage);
        ProductDescription = findViewById(R.id.shop_ProductDescription);
        ProductName = findViewById(R.id.shop_ProductName);
        ProductPrice = findViewById(R.id.shop_ProductPrice);

        Intent intent = getIntent();
        prodName = String.valueOf(intent.getStringExtra("productID"));
        Log.d("cc: ", prodName);

        showCurrentProductInfo(ProductName, ProductDescription, ProductPrice);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });


    }

    //luu du lieu moi vao database
    private void saveData() {
        DatabaseReference prodRef = FirebaseDatabase.getInstance().getReference();
        prodRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child("Products").child(prodName).exists()) {
                    HashMap<String, Object> productInfo = new HashMap<>();

                    productInfo.put("title", ProductName.getText().toString());
                    productInfo.put("description", ProductDescription.getText().toString());
                    productInfo.put("price", Integer.parseInt(ProductPrice.getText().toString()));

                    prodRef.child("Products").child(prodName).updateChildren(productInfo)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ShopEditProductDetails.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ShopEditProductDetails.this, HomePage.class);
                                        startActivity(intent);
                                    } else {

                                        Toast.makeText(ShopEditProductDetails.this, "Đã xảy ra lỗi khi cập nhật, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    //Lay thong tin tu database ve
    private void showCurrentProductInfo(EditText productName, EditText productDescription, EditText productPrice) {

        DatabaseReference prodRef = FirebaseDatabase.getInstance().getReference().child("Products");
        prodRef.child(prodName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    if (snapshot.exists()){
                        String prodName = snapshot.child("title").getValue().toString();
                        String prodDescription = snapshot.child("description").getValue().toString();
                        String prodPrice = snapshot.child("price").getValue().toString();


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