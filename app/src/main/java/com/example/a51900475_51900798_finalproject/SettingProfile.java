package com.example.a51900475_51900798_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingProfile extends AppCompatActivity {
    private EditText profile_username, profile_phone, profile_password, profile_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profile);

        profile_username = findViewById(R.id.profile_username);
        profile_phone = findViewById(R.id.profile_phone);
        profile_password = findViewById(R.id.profile_password);
        profile_email = findViewById(R.id.profile_email);

        showUserInfo(profile_username, profile_phone, profile_password, profile_email);
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