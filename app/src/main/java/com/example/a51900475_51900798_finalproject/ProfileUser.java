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
    TextView tvHotSales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        profileUserRV = findViewById(R.id.profileUserRV);
        tvHotSales = findViewById(R.id.tvHotSales);


        listUserExtensions.add("Đã thích");
        listUserExtensions.add("Đánh giá của tôi");
        listUserExtensions.add("Thiết lập tài khoản");

        profileUserExtensionsAdapter = new ProfileUserExtensionsAdapter(listUserExtensions);

        profileUserRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        profileUserRV.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        profileUserRV.setAdapter(profileUserExtensionsAdapter);




        profileUserHotSalesRV = findViewById(R.id.profileUserHotSalesRV);

//        listProducts.add(new Product("Giày",299000,4.2,1.2,R.drawable.sneaker1));
//        listProducts.add(new Product("Bàn phím",599000,4.6,1.2,R.drawable.keyboard3));
        listProducts.add(new Product("Quần áo",150000,5.0,2.5,R.drawable.shirt,"clothes"));
        productAdapter = new ProductAdapter();

        productAdapter.setData(listProducts);

        profileUserHotSalesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        profileUserHotSalesRV.setAdapter(productAdapter);

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