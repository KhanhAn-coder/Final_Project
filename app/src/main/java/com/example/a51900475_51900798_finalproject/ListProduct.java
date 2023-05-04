package com.example.a51900475_51900798_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import product.Product;
import product.ProductAdapter;
import product.ProductViewHolder;
import product.Productss;

public class ListProduct extends AppCompatActivity {
    RecyclerView rv_mouseProduct;
    TextView tvType;
    ProductAdapter mouseProductAdapter;
    ArrayList<Product> listMouseProduct = new ArrayList<>();
    ImageButton imgButtonBack;
    DatabaseReference RootRef;
    Query query;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        RootRef = FirebaseDatabase.getInstance().getReference("Products");
        rv_mouseProduct = findViewById(R.id.rv_mouseProduct);
        rv_mouseProduct.setHasFixedSize(true);
        rv_mouseProduct.setLayoutManager(new GridLayoutManager(this,2));

        tvType = findViewById(R.id.tvType);




        imgButtonBack = findViewById(R.id.imgButtonBack);
        imgButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListProduct.this, HomePage.class);
                intent.putExtra("code","success");
                startActivity(intent);
            }
        });
        Intent intent = getIntent();

        String type = intent.getStringExtra("type");
        tvType.setText(type);
        /*switch (type){
            case "mouse":
                query = RootRef.orderByChild("type").equalTo("mouse_product");
                break;
            case "watch":
                query = RootRef.orderByChild("type").equalTo("watch_product");
                break;
            case "camera":
                query = RootRef.orderByChild("type").equalTo("camera_product");
                break;
            case "keyboard":
                query = RootRef.orderByChild("type").equalTo("keyboard_product");
                break;
            case "shoes":
                query = RootRef.orderByChild("type").equalTo("shoes_product");
                break;
            case "clothes":
                query = RootRef.orderByChild("type").equalTo("clothes_product");
                break;
        }*/

        query = RootRef.orderByChild("type").equalTo(type);
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
                            Intent intent = new Intent(ListProduct.this,ProuctDetails.class);
                            Bundle extras = new Bundle();
                            extras.putString("productID",model.getProductID());
                            extras.putString("type",model.getType());
                            //intent.putExtra("productID",model.getProductID());
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
        rv_mouseProduct.setAdapter(adapter);
        adapter.startListening();


        //options = new FirebaseRecyclerOptions.Builder<Product>().setQuery(RootRef,Product.class);

    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}