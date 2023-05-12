package userOrders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a51900475_51900798_finalproject.Interface.ItemClickListener;
import com.example.a51900475_51900798_finalproject.R;

public class UserOrdersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvUserOrdersShopID, tvUserOdersPhoneNumber, tvUserOrsersAddress, tvUserOrdersTotalPrice, tvUserOrdersStatus;
    public ItemClickListener listener;
    public UserOrdersViewHolder(@NonNull View itemView) {
        super(itemView);
        tvUserOrdersShopID = itemView.findViewById(R.id.tvUserOrdersShopID);
        tvUserOdersPhoneNumber = itemView.findViewById(R.id.tvUserOdersPhoneNumber);
        tvUserOrsersAddress = itemView.findViewById(R.id.tvUserOrsersAddress);
        tvUserOrdersTotalPrice = itemView.findViewById(R.id.tvUserOrdersTotalPrice);
        tvUserOrdersStatus = itemView.findViewById(R.id.tvUserOrdersStatus);

    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        listener.onClick(view,getAdapterPosition(),false);
    }
}
