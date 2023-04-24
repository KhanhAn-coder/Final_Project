package product;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a51900475_51900798_finalproject.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<Product> mProduct;

    public void setData(ArrayList<Product> arrayList){
        this.mProduct = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = mProduct.get(position);
        if (product == null){
            return;
        }

        holder.imageView_item.setImageResource(product.getSourceID());
        holder.textView_title.setText(product.getTitle());
        holder.textView_price.setText(String.valueOf(product.getPrice())+"đ");
        holder.textView_rating.setText(String.valueOf(product.getRating()));
        holder.textView_sold.setText(String.valueOf(product.getSold())+"k");

    }

    @Override
    public int getItemCount() {
        if(mProduct != null){
            return mProduct.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_item;
        private TextView textView_title, textView_price, textView_rating, textView_sold;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_item = itemView.findViewById(R.id.imageView_item);
            textView_title = itemView.findViewById(R.id.textView_title);
            textView_price = itemView.findViewById(R.id.textView_price);
            textView_rating = itemView.findViewById(R.id.textView_rating);
            textView_sold = itemView.findViewById(R.id.textView_sold);
        }
    }
}
