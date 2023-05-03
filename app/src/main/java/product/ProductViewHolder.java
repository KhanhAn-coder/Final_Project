package product;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a51900475_51900798_finalproject.Interface.ItemClickListener;
import com.example.a51900475_51900798_finalproject.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView imageView_item;
    public TextView textView_title, textView_price, textView_rating, textView_sold;
    public ItemClickListener listener;

    public ProductViewHolder(View itemView) {
        super(itemView);
        imageView_item = itemView.findViewById(R.id.imageView_item);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_price = itemView.findViewById(R.id.textView_price);
        textView_rating = itemView.findViewById(R.id.textView_rating);
        textView_sold = itemView.findViewById(R.id.textView_sold);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition(), false);
    }
}
