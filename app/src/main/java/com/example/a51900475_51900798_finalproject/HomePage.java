package com.example.a51900475_51900798_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

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

public class HomePage extends AppCompatActivity {
    ImageButton imgButtonCart, imgButtonChat;
    private ViewPager viewPager;
    CircleIndicator circleIndicator;
    private BannerAdapter bannerAdapter;
    private RecyclerView recyclerView_Category;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView_KindProduct;
    private ArrayList<KindProduct> listKindProduct = new ArrayList<>();
    private KindProductAdapter kindProductAdapter;
    private BottomNavigationView bottomNavigation;
    private Handler handler;
    private Runnable update;
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
        listKindProduct.add(new KindProduct(R.drawable.keyboardknid,"Máy ảnh"));
        listKindProduct.add(new KindProduct(R.drawable.keyboard,"Chuột máy tính"));
        listKindProduct.add(new KindProduct(R.drawable.keyboard,"Đồng hồ"));
        listKindProduct.add(new KindProduct(R.drawable.keyboard,"Bàn phím"));
        listKindProduct.add(new KindProduct(R.drawable.keyboard,"Giày dép"));
        listKindProduct.add(new KindProduct(R.drawable.keyboard,"Quần áo"));

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
}