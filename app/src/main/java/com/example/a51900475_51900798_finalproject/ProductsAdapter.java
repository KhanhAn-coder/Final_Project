package com.example.a51900475_51900798_finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
    ArrayList<Products> listProducts;

    public ProductsAdapter(ArrayList<Products> listProducts){
        this.listProducts = listProducts;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.products,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Products products = listProducts.get(position);
        holder.tvTitle.setText(products.getTitle());
        holder.tvPrice.setText(String.valueOf(products.getPrice()));
        holder.tvRating.setText(String.valueOf(products.getRating()));
        holder.tvSold.setText(String.valueOf(products.getSold()));

    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView tvTitle, tvPrice, tvRating, tvSold;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvSold = itemView.findViewById(R.id.tvSold);

        }
    }
}
