package com.example.a51900475_51900798_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;

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
    private int currentPage = 0;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;
    private RecyclerView recyclerView_Category;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView_KindProduct;
    private ArrayList<KindProduct> listKindProduct = new ArrayList<>();
    private KindProductAdapter kindProductAdapter;
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

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        };



        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_MS,PERIOD_MS);

        imgButtonCart = findViewById(R.id.imgButtonCart);
        imgButtonChat = findViewById(R.id.imgButtonChat);

        //Các category, sản phẩm

        recyclerView_Category = findViewById(R.id.recyclerView_Category);
        categoryAdapter = new CategoryAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView_Category.setLayoutManager(linearLayoutManager);

        categoryAdapter.setData(getListCategory());
        recyclerView_Category.setAdapter(categoryAdapter);

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








    }

    private ArrayList<Category> getListCategory() {
        ArrayList<Category> listCategory = new ArrayList<>();

        ArrayList<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product("Giày",299000,4.2,1.2,R.drawable.sneaker1));
        listProduct.add(new Product("Bàn phím",599000,4.6,1.2,R.drawable.keyboard3));

        listCategory.add(new Category("Gợi ý hôm nay", listProduct));
        return listCategory;
    }

    private List<Banner> getListBanner(){
        List<Banner> list = new ArrayList<>();
        list.add(new Banner(R.drawable.banner1));
        list.add(new Banner(R.drawable.banner2));
        return list;
    }
}