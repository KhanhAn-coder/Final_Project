package com.example.a51900475_51900798_finalproject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    private final Context mContext;
    private final List<Banner> mListBanner;
    public BannerAdapter(List<Banner> mListBanner, Context mContext) {
        this.mListBanner = mListBanner;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.banner_photo, container, false);
        ImageView imgBanner = view.findViewById(R.id.img_Banner);

        Banner banner = mListBanner.get(position);
        if (banner != null) {
            Glide.with(mContext).load(banner.getBannerID()).into(imgBanner);
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);


    }

    @Override
    public int getCount() {
        if(mListBanner != null){
            return mListBanner.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
