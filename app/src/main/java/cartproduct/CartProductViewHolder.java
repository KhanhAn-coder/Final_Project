package cartproduct;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a51900475_51900798_finalproject.Interface.ItemClickListener;
import com.example.a51900475_51900798_finalproject.R;

public class CartProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvCartName, tvCartPrice, tvCartAmount;
    public ImageView imageViewCartProduct;
    public ItemClickListener listener;

    public CartProductViewHolder(View itemView) {
        super(itemView);
        tvCartName = itemView.findViewById(R.id.tvCartName);
        tvCartPrice = itemView.findViewById(R.id.tvCartPrice);
        tvCartAmount = itemView.findViewById(R.id.tvCartAmount);
        imageViewCartProduct = itemView.findViewById(R.id.imageViewCartProduct);


    }
    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition(), false);
    }

}
