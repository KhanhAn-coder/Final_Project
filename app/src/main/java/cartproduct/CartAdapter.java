package cartproduct;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a51900475_51900798_finalproject.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    ArrayList<CartProduct> listCartProduct;

    public void setData(ArrayList<CartProduct> listcartProduct){
        this.listCartProduct = listcartProduct;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.cart_product_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartProduct cartProduct = listCartProduct.get(position);
        holder.imageViewCartProduct.setImageResource(cartProduct.getResourceID());
        holder.tvCartName.setText(cartProduct.getTitle());
        holder.tvCartAmount.setText(String.valueOf(cartProduct.getAmount()));
        holder.tvCartPrice.setText(String.valueOf(cartProduct.getPrice()));

    }

    @Override
    public int getItemCount() {
        return listCartProduct.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewCartProduct;
        TextView tvCartName, tvCartPrice, tvCartAmount;
        ImageButton imgButtonAddCart, imgButtonRemoveCart;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCartProduct = itemView.findViewById(R.id.imageViewCartProduct);
            tvCartName = itemView.findViewById(R.id.tvCartName);
            tvCartPrice = itemView.findViewById(R.id.tvCartPrice);
            tvCartAmount = itemView.findViewById(R.id.tvCartAmount);

            imgButtonAddCart = itemView.findViewById(R.id.imgButtonAdd);

        }
    }
}
