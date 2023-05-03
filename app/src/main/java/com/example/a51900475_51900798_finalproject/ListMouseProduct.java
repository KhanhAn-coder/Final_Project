package com.example.a51900475_51900798_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import product.Product;
import product.ProductAdapter;
import product.ProductViewHolder;
import product.Productss;

public class ListMouseProduct extends AppCompatActivity {
    RecyclerView rv_mouseProduct;
    ProductAdapter mouseProductAdapter;
    ArrayList<Product> listMouseProduct = new ArrayList<>();
    ImageButton imgButtonBack;
    DatabaseReference RootRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mouse_product);
        RootRef = FirebaseDatabase.getInstance().getReference("Products");
        rv_mouseProduct = findViewById(R.id.rv_mouseProduct);
        rv_mouseProduct.setHasFixedSize(true);
        rv_mouseProduct.setLayoutManager(new GridLayoutManager(this,2));



        /*listMouseProduct.add(new Product("Chuột máy tính 1",390000,4.6,1.2,R.drawable.mouse_1, "mouse"));
        listMouseProduct.add(new Product("Chuột máy tính 2",279000,4.5,2.5,R.drawable.mouse_2,"mouse"));
        listMouseProduct.add(new Product("Chuột máy tính 3",599000,4.8,1.6,R.drawable.mouse_3,"mouse"));
        listMouseProduct.add(new Product("Chuột máy tính 4",259000,5.0,1.9,R.drawable.mouse_4,"mouse"));
        listMouseProduct.add(new Product("Chuột máy tính 5",490000,4.3,1.8,R.drawable.mouse_5,"mouse"));
        listMouseProduct.add(new Product("Chuột máy tính 6",649000,4.5,2.7,R.drawable.mouse_6,"mouse"));
*/
       /* mouseProductAdapter = new ProductAdapter();
        mouseProductAdapter.setData(listMouseProduct);

        rv_mouseProduct.setLayoutManager(new GridLayoutManager(this,2));
        rv_mouseProduct.setAdapter(mouseProductAdapter);*/

        imgButtonBack = findViewById(R.id.imgButtonBack);
        imgButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListMouseProduct.this, HomePage.class);
                intent.putExtra("code","success");
                startActivity(intent);
            }
        });
        Query query = RootRef.orderByChild("type").equalTo("mouse_product");
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
                    
            }




            @Override
            public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
                ProductViewHolder holder = new ProductViewHolder(view);
                return holder;
            }
        };
        rv_mouseProduct.setAdapter(adapter);
        adapter.startListening();


        //options = new FirebaseRecyclerOptions.Builder<Product>().setQuery(RootRef,Product.class);

    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}