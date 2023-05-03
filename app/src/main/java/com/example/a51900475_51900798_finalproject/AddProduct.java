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


import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Random;

public class AddProduct extends AppCompatActivity {
    private String category, description, price, name_Product, imageLink , productID;
    Button btn_AddProduct;
    ImageView ProductImage;
    EditText ProductName, ProductDescription, ProductPrice;
    private static final int imagePick = 1;
    private Uri imageUri;
    private int randomKey;
    private StorageReference ProductImageRef;
    private DatabaseReference ProductRef;

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null){
                        Intent intent = result.getData();
                        imageUri = result.getData().getData();
                        ProductImage.setImageURI(imageUri);

                    }
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        category = getIntent().getExtras().get("Category").toString();

        btn_AddProduct = findViewById(R.id.btn_AddProduct);
        ProductImage = findViewById(R.id.ProductImage);
        ProductName = findViewById(R.id.ProductName);
        ProductDescription = findViewById(R.id.ProductDescription);
        ProductPrice = findViewById(R.id.ProductPrice);
        ProductImageRef = FirebaseStorage.getInstance().getReference().child("sourceID");
        ProductRef = FirebaseDatabase.getInstance().getReference().child("Products");


        ProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageGallery();
            }
        });
        btn_AddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductData();
            }
        });

    }

    private void ProductData() {

        description = ProductDescription.getText().toString();
        price = ProductPrice.getText().toString();
        name_Product = ProductName.getText().toString();

        if (imageUri == null){
            Toast.makeText(this, "Vui lòng chọn ảnh cho sản phẩm", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(price)){
            Toast.makeText(this, "Vui lòng nhập giá sản phẩm", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(description)){
            Toast.makeText(this, "Vui lòng nhập thông tin sản phẩm", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(name_Product)){
            Toast.makeText(this, "Vui lòng nhập tên sản phẩm", Toast.LENGTH_SHORT).show();
        }
        else
        {

            storagedProductInformation();
        }
    }

    public static int getRandomNumber(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }

    private void storagedProductInformation() {
        randomKey = getRandomNumber(10000, 99999);
        productID = name_Product + String.valueOf(randomKey);

        StorageReference filePath = ProductImageRef.child(imageUri.getLastPathSegment() + productID + ".png");

        final UploadTask uploadTask =filePath.putFile(imageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(AddProduct.this, "Không upload được", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddProduct.this, "Upload ảnh thành công", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            throw task.getException();
                        }
                        imageLink = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                            imageLink = task.getResult().toString();
                            Toast.makeText(AddProduct.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                            saveImageToDatabase();
                        }
                    }
                });
            }
        });


    }

    private void saveImageToDatabase() {
        HashMap<String, Object> productMap = new HashMap<>();

        productMap.put("productID", productID);
        productMap.put("description", description);
        productMap.put("price", Integer.parseInt(price));
        productMap.put("title", name_Product);
        productMap.put("sourceID", imageLink);
        productMap.put("type", category);
        productMap.put("sold", Double.valueOf(getRandomNumber(1000, 9999)));
        productMap.put("rating", Double.valueOf(getRandomNumber(0,5)));

        ProductRef.child(productID).updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(AddProduct.this, "Put successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void openImageGallery() {
        Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mActivityResultLauncher.launch(imageIntent);

    }
}