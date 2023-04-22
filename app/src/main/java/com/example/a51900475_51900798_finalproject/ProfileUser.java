package com.example.a51900475_51900798_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class ProfileUser extends AppCompatActivity {
    ArrayList<String> listUserExtensions = new ArrayList<>();
    RecyclerView profileUserRV;
    ProfileUserExtensionsAdapter profileUserExtensionsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        profileUserRV = findViewById(R.id.profileUserRV);

        listUserExtensions.add("Đã thích");
        listUserExtensions.add("Đánh giá của tôi");
        listUserExtensions.add("Thiết lập tài khoản");

        profileUserExtensionsAdapter = new ProfileUserExtensionsAdapter(listUserExtensions);

        profileUserRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        profileUserRV.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        profileUserRV.setAdapter(profileUserExtensionsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}