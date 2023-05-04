package com.example.a51900475_51900798_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import product.Product;
import product.ProductAdapter;

public class ProfileUser extends AppCompatActivity {
    ArrayList<String> listUserExtensions = new ArrayList<>();
    ArrayList<Product> listProducts = new ArrayList<>();
    RecyclerView profileUserRV;
    RecyclerView profileUserHotSalesRV;
    ProfileUserExtensionsAdapter profileUserExtensionsAdapter;
    BottomNavigationView bottomNavigationUserProfile;

    ProductAdapter productAdapter;
    TextView tvHotSales, profile_tv_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        profileUserRV = findViewById(R.id.profileUserRV);
        tvHotSales = findViewById(R.id.tvHotSales);
        profile_tv_username = findViewById(R.id.profile_tv_username);
        profile_tv_username.setText(LoggedUser.loggedUser.getUsername());

        listUserExtensions.add("Đã thích");
        listUserExtensions.add("Đánh giá của tôi");
        listUserExtensions.add("Thiết lập tài khoản");

        profileUserExtensionsAdapter = new ProfileUserExtensionsAdapter(listUserExtensions);

        profileUserRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        profileUserRV.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        profileUserRV.setAdapter(profileUserExtensionsAdapter);




        profileUserHotSalesRV = findViewById(R.id.profileUserHotSalesRV);



        //Bottom Navigation
        bottomNavigationUserProfile = findViewById(R.id.bottomNavigationUserProfile);
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            int selectedItemId = extras.getInt("selectedItemID");
            bottomNavigationUserProfile.setSelectedItemId(selectedItemId);
        }

        bottomNavigationUserProfile.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_userprofile:
                        Toast.makeText(ProfileUser.this, "Đang ở UserProfile", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_home:
                        Intent intent = new Intent(ProfileUser.this,HomePage.class);
                        Bundle extras = new Bundle();
                        extras.putString("navigate","homepage");
                        extras.putInt("selectedItemID_home",item.getItemId());
                        intent.putExtras(extras);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });




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