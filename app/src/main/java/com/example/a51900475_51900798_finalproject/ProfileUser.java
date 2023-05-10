package com.example.a51900475_51900798_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import product.Product;
import product.ProductAdapter;
import product.ProductViewHolder;
import product.Productss;

public class ProfileUser extends AppCompatActivity {
    ArrayList<String> listUserExtensions = new ArrayList<>();
    ArrayList<Product> listProducts = new ArrayList<>();
    RecyclerView profileUserRV;
    RecyclerView profileUserHotSalesRV;
    ProfileUserExtensionsAdapter profileUserExtensionsAdapter;
    BottomNavigationView bottomNavigationUserProfile;
    DatabaseReference RootRef;

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
        listUserExtensions.add("Bắt đầu bán hàng");
        listUserExtensions.add("Đánh giá của tôi");
        listUserExtensions.add("Thiết lập tài khoản");

        profileUserExtensionsAdapter = new ProfileUserExtensionsAdapter(listUserExtensions);

        profileUserRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        profileUserRV.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        profileUserRV.setAdapter(profileUserExtensionsAdapter);



        RootRef = FirebaseDatabase.getInstance().getReference("Products");
        profileUserHotSalesRV = findViewById(R.id.profileUserHotSalesRV);

        profileUserHotSalesRV.setHasFixedSize(true);
        profileUserHotSalesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        Query query = RootRef.orderByChild("type").equalTo("sales_product");
        FirebaseRecyclerOptions<Productss> options = new FirebaseRecyclerOptions.Builder<Productss>()
                .setQuery(query,Productss.class)
                .build();

        FirebaseRecyclerAdapter<Productss, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Productss, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(ProductViewHolder holder, int position, Productss model) {

                holder.textView_title.setText(model.getTitle());
                holder.textView_price.setText(model.getPrice()+"đ");
                holder.textView_rating.setText(String.valueOf(model.getRating()));
                holder.textView_sold.setText(String.valueOf(model.getSold())+"k");
                Picasso.get().load(model.getSourceID()).into(holder.imageView_item);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ProfileUser.this,ProuctDetails.class);
                        Bundle extras = new Bundle();
                        extras.putString("productID",model.getProductID());
                        extras.putString("type",model.getType());
                        extras.putString("sourceID", model.getSourceID());
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
                ProductViewHolder holder = new ProductViewHolder(view);
                return holder;
            }
        };
        profileUserHotSalesRV.setAdapter(adapter);
        adapter.startListening();



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

        switch (item.getItemId()){
            case R.id.item_settings:

        }
        return super.onOptionsItemSelected(item);
    }
}