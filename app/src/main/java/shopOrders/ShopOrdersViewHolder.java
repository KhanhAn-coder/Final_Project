package shopOrders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a51900475_51900798_finalproject.Interface.ItemClickListener;
import com.example.a51900475_51900798_finalproject.R;

public class ShopOrdersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvShopCheckOrdersUserName, tvShopCheckOrdersPhoneNumbers, tvShopCheckOrdersAddress, tvShopCheckOrdersPrice;
    public Button btnShowOrderProducts;
    public ItemClickListener listener;

    public ShopOrdersViewHolder(View itemView) {
        super(itemView);
        tvShopCheckOrdersUserName = itemView.findViewById(R.id.tvShopCheckOrdersUserName);
        tvShopCheckOrdersPhoneNumbers = itemView.findViewById(R.id.tvShopCheckOrdersPhoneNumbers);
        tvShopCheckOrdersAddress = itemView.findViewById(R.id.tvShopCheckOrdersAddress);
        tvShopCheckOrdersPrice = itemView.findViewById(R.id.tvShopCheckOrdersPrice);
        btnShowOrderProducts = itemView.findViewById(R.id.btnShowOrderProducts);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition(),false);

    }
}
