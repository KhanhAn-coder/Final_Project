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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SettingProfile extends AppCompatActivity {
    private EditText profile_username, profile_phone, profile_password, profile_email;
    ImageView userAvatar;
    Button btn_update;

    private Uri imageUri;



    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null){

                        imageUri = result.getData().getData();
                        userAvatar.setImageURI(imageUri);

                    }
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profile);

        userAvatar = findViewById(R.id.user_Avatar);
        profile_username = findViewById(R.id.profile_username);
        profile_phone = findViewById(R.id.profile_phone);
        profile_password = findViewById(R.id.profile_password);
        profile_email = findViewById(R.id.profile_email);
        btn_update = findViewById(R.id.btn_update);

        showUserInfo(profile_username, profile_phone, profile_password, profile_email);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInfo();
            }
        });


        userAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageGallery();
            }
        });
    }

    private void openImageGallery() {
        Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mActivityResultLauncher.launch(imageIntent);

    }

    private void updateInfo() {


        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference();
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.child("Users").child(LoggedUser.loggedUser.getPhone()).exists()) {
                        HashMap<String, Object> userInfo = new HashMap<>();

                        userInfo.put("Username", profile_username.getText().toString());
                        userInfo.put("Password", profile_password.getText().toString());
                        userInfo.put("PhoneToOrder", profile_phone.getText().toString());
                        userInfo.put("Email", profile_email.getText().toString());

                        userRef.child("Users").child(LoggedUser.loggedUser.getPhone()).updateChildren(userInfo)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SettingProfile.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SettingProfile.this, HomePage.class);
                                            startActivity(intent);
                                        } else {

                                            Toast.makeText(SettingProfile.this, "Đã xảy ra lỗi khi đăng ký, vui lòng thử lại", Toast.LENGTH_SHORT).show();
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

    private void showUserInfo(EditText profile_username, EditText profile_phone, EditText profile_password, EditText profile_email) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(LoggedUser.loggedUser.getPhone());

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    if (snapshot.child("Phone").exists()){
                        String username = snapshot.child("Username").getValue().toString();
                        String email = snapshot.child("Email").getValue().toString();
                        String phone = snapshot.child("Phone").getValue().toString();
                        String password = snapshot.child("Password").getValue().toString();

                        profile_username.setText(username);
                        profile_email.setText(email);
                        profile_phone.setText(phone);
                        profile_password.setText(password);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}