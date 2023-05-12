package com.example.a51900475_51900798_finalproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a51900475_51900798_finalproject.Users.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileUserExtensionsAdapter extends RecyclerView.Adapter<ProfileUserExtensionsAdapter.MyViewHolder> {
    ArrayList<String> listUserExtensions;
    private Context mContext;

    public ProfileUserExtensionsAdapter(ArrayList<String> listUserExtensions){
        this.listUserExtensions = listUserExtensions;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_extension,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.extensions.setText(listUserExtensions.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return listUserExtensions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView extensions;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            extensions = itemView.findViewById(R.id.extension);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(extensions.getText().toString().equals("Đã thích")){
                        Toast.makeText(itemView.getContext(), "Đã thích", Toast.LENGTH_SHORT).show();
                    }else if (extensions.getText().toString().equals("Đánh giá của tôi")){
                        Toast.makeText(itemView.getContext(), "Đánh giá của tôi", Toast.LENGTH_SHORT).show();
                    }else if (extensions.getText().toString().equals("Thiết lập tài khoản")){
                        Toast.makeText(itemView.getContext(), "Thiết lập tài khoản", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(itemView.getContext(), SettingProfile.class);
                        itemView.getContext().startActivity(intent);
                    }
                    else if (extensions.getText().toString().equals("Bắt đầu bán hàng"))
                    {
                        checkValid(LoggedUser.loggedUser.getPhone(),itemView.getContext());
                    } else if (extensions.getText().toString().equals("Đăng xuất")) {
                        Intent intent = new Intent(itemView.getContext(), SignIn.class);
                        itemView.getContext().startActivity(intent);

                    }
                }
            });
        }
    }

    public void checkValid(String userPhone, Context mContext){

        final DatabaseReference valid = FirebaseDatabase.getInstance().getReference();
        Boolean check;
        valid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child("Users").child(userPhone).exists()) {

                    String currentUser = LoggedUser.loggedUser.getShopID();
//                    Log.d("getShopID: ", LoggedUser.loggedUser.getShopID());
                    if (currentUser != null) {
                        Intent intent = new Intent( mContext , UserShop.class);
                        mContext.startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent( mContext , ShopSignUp.class);
                        mContext.startActivity(intent);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}
