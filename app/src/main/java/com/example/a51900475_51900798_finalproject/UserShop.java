package com.example.a51900475_51900798_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.api.LogDescriptor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.auth.User;
import com.squareup.picasso.Picasso;

import product.ProductViewHolder;
import product.Productss;

public class UserShop extends AppCompatActivity {
    Button userShop_btnAdd, userShop_btnCheckOrders;
    ImageView btn_back;
    DatabaseReference RootRef;
    private RecyclerView userShop_rvProduct;
    BottomNavigationView bottomNavigationUserProfile;
    TextView userShop_shopName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_shop);

        userShop_shopName = findViewById(R.id.userShop_shopName);
        userShop_shopName.setText(LoggedUser.loggedUser.getShopID());

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserShop.this, ProfileUser.class);
                startActivity(intent);
            }
        });


        userShop_btnAdd = findViewById(R.id.userShop_btnAdd);
        userShop_btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserShop.this, AddProductCategory.class);
                startActivity(intent);
            }
        });

        userShop_btnCheckOrders = findViewById(R.id.userShop_btnCheckOrders);
        userShop_btnCheckOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserShop.this, ShopCheckOrders.class);
                intent.putExtra("shopID",LoggedUser.loggedUser.getShopID());
                startActivity(intent);
            }
        });


        userShop_rvProduct = findViewById(R.id.userShop_rvProduct);
        RootRef = FirebaseDatabase.getInstance().getReference().child("Products");
        userShop_rvProduct.setHasFixedSize(true);
        userShop_rvProduct.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        Query query = RootRef.orderByChild("shopID").equalTo(LoggedUser.loggedUser.getShopID());
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
                        CharSequence options[] = new CharSequence[]{
                                "Edit", "Remove"
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserShop.this);
                        builder.setTitle("UserShop Options:");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i==0){
                                    Intent intent = new Intent(UserShop.this,ShopEditProductDetails.class);
                                    Bundle extras = new Bundle();
                                    extras.putString("productID", model.getProductID());
                                    Log.d("ProductID", model.getProductID());
                                    intent.putExtras(extras);
                                    startActivity(intent);
                                }
                                if (i==1){
                                    RootRef
                                            .child(model.getProductID())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(Task<Void> task) {
                                                    Toast.makeText(UserShop.this, "Removed Successful", Toast.LENGTH_SHORT).show();
                                                    Log.d("ProductID: ",model.getProductID());

                                                    Intent intent = new Intent(UserShop.this, HomePage.class);
                                                    startActivity(intent);
                                                }
                                            });
                                }
                            }

                        });
                        builder.show();



//                        Intent intent = new Intent(UserShop.this, ProuctDetails.class);
//                        Bundle extras = new Bundle();
//                        extras.putString("productID",model.getProductID());
//                        extras.putString("type",model.getType());
//                        extras.putString("sourceID", model.getSourceID());
//                        intent.putExtras(extras);
//                        startActivity(intent);
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
        userShop_rvProduct.setAdapter(adapter);
        adapter.startListening();


        //Bottom navigation

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
                        Toast.makeText(UserShop.this, "Đang ở UserProfile", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_home:
                        Intent intent = new Intent(UserShop.this,HomePage.class);
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