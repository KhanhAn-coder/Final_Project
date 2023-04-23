package category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.a51900475_51900798_finalproject.R;

import java.util.ArrayList;

import product.ProductAdapter;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context mContext;
    private ArrayList<Category> mListCategory;

    public CategoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<Category> list){
        this.mListCategory = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = mListCategory.get(position);
        if(category == null){
            return;
        }

        holder.tv_nameCategory.setText(category.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.rcv_product.setLayoutManager(linearLayoutManager);

        ProductAdapter productAdapter = new ProductAdapter();
        productAdapter.setData(category.getProducts());
        holder.rcv_product.setAdapter(productAdapter);


    }

    @Override
    public int getItemCount() {
        if(mListCategory != null){
            return mListCategory.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nameCategory;
        private RecyclerView rcv_product;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nameCategory = itemView.findViewById(R.id.textView_category);
            rcv_product = itemView.findViewById(R.id.recyclerView_item);
        }
    }
}
