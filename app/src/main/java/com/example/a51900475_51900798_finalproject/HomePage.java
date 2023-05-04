package com.example.a51900475_51900798_finalproject;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import kindProduct.KindProduct;
import category.Category;
import category.CategoryAdapter;
import kindProduct.KindProductAdapter;
import me.relex.circleindicator.CircleIndicator;
import product.Product;
import product.ProductViewHolder;
import product.Productss;

public class HomePage extends AppCompatActivity {
    ImageButton imgButtonCart, imgButtonChat;
    private ViewPager viewPager;
    CircleIndicator circleIndicator;
    private BannerAdapter bannerAdapter;
    private RecyclerView rv_hotSales;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView_KindProduct;
    private ArrayList<KindProduct> listKindProduct = new ArrayList<>();
    private KindProductAdapter kindProductAdapter;
    private BottomNavigationView bottomNavigation;
    private Handler handler;
    private Runnable update;
    DatabaseReference RootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        //Thanh tìm kiếm, giỏ hàng và chat, banner quảng cáo

        viewPager = findViewById(R.id.viewPager);
        circleIndicator = findViewById(R.id.circleIndicator);

        bannerAdapter = new BannerAdapter(getListBanner(), this);

        viewPager.setAdapter(bannerAdapter);
        circleIndicator.setViewPager(viewPager);
        bannerAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

         handler = new Handler();
         update = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int count = viewPager.getAdapter().getCount();
                if(currentItem == count-1){
                    viewPager.setCurrentItem(0);
                }else {
                    viewPager.setCurrentItem(currentItem+1);
                }
                handler.postDelayed(this,3000);
            }


        };
         handler.postDelayed(update,3000);








        imgButtonCart = findViewById(R.id.imgButtonCart);
        imgButtonChat = findViewById(R.id.imgButtonChat);

        //Các category, sản phẩm

//        recyclerView_Category = findViewById(R.id.recyclerView_Category);
//        categoryAdapter = new CategoryAdapter(this);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        recyclerView_Category.setLayoutManager(linearLayoutManager);
//
//        categoryAdapter.setData(getListCategory());
//        recyclerView_Category.setAdapter(categoryAdapter);

        // Các loại sản phẩm
        recyclerView_KindProduct = findViewById(R.id.recyclerView_KindProduct);
        listKindProduct.add(new KindProduct(R.drawable.camerakind,"Máy ảnh"));
        listKindProduct.add(new KindProduct(R.drawable.mousekind,"Chuột máy tính"));
        listKindProduct.add(new KindProduct(R.drawable.watchkind,"Đồng hồ"));
        listKindProduct.add(new KindProduct(R.drawable.keyboardknid,"Bàn phím"));
        listKindProduct.add(new KindProduct(R.drawable.sneakerkind,"Giày dép"));
        listKindProduct.add(new KindProduct(R.drawable.cloteskind,"Quần áo"));

        kindProductAdapter = new KindProductAdapter(listKindProduct);
        recyclerView_KindProduct.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView_KindProduct.setAdapter(kindProductAdapter);

        // Bottom Navigation

        bottomNavigation = findViewById(R.id.bottomNavigation);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            int selectedItemId = extras.getInt("selectedItemID_home");
            bottomNavigation.setSelectedItemId(selectedItemId);
        }

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Toast.makeText(HomePage.this, "Đang ở homepage", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_userprofile:
                        Intent intent = new Intent(HomePage.this,ProfileUser.class);
                        Bundle extras = new Bundle();
                        extras.putString("navigate","userprofile");
                        extras.putInt("selectedItemID", item.getItemId());
                        intent.putExtras(extras);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });



        //Lấy sản phẩm gợi ý hôm nay từ Firebase

        RootRef = FirebaseDatabase.getInstance().getReference("Products");
        rv_hotSales = findViewById(R.id.recyclerView_Category);
        rv_hotSales.setHasFixedSize(true);
        rv_hotSales.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
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
                        Intent intent = new Intent(HomePage.this,ProuctDetails.class);
                        Bundle extras = new Bundle();
                        extras.putString("productID",model.getProductID());
                        extras.putString("type",model.getType());
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
        rv_hotSales.setAdapter(adapter);
        adapter.startListening();


    }

//    private ArrayList<Category> getListCategory() {
//        ArrayList<Category> listCategory = new ArrayList<>();
//
//        ArrayList<Product> listProduct = new ArrayList<>();
//        listProduct.add(new Product("Giày",299000,4.2,1.2,R.drawable.sneaker1,"mouse"));
//        listProduct.add(new Product("Bàn phím",599000,4.6,1.2,R.drawable.keyboard3, "keyboard"));
//
//        listCategory.add(new Category("Gợi ý hôm nay", listProduct));
//        return listCategory;
//    }

    private List<Banner> getListBanner(){
        List<Banner> list = new ArrayList<>();
        list.add(new Banner(R.drawable.banner1));
        list.add(new Banner(R.drawable.banner2));
        return list;
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(update);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}