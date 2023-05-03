package kindProduct;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a51900475_51900798_finalproject.ListMouseProduct;
import com.example.a51900475_51900798_finalproject.R;

import java.util.ArrayList;

public class KindProductAdapter extends RecyclerView.Adapter<KindProductAdapter.KindProductViewHolder> {
    ArrayList<KindProduct> listKindProduct;

    public KindProductAdapter(ArrayList<KindProduct> listKindProduct) {
        this.listKindProduct = listKindProduct;
    }

    @NonNull
    @Override
    public KindProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KindProductViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.kind_product_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull KindProductViewHolder holder, int position) {
        KindProduct kindProduct = listKindProduct.get(position);
        holder.imageViewKindProduct.setImageResource(kindProduct.getResourceID());
        holder.tvTitleKindProduct.setText(kindProduct.getTitle());

    }

    @Override
    public int getItemCount() {
        return listKindProduct.size();
    }

    public class KindProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewKindProduct;
        TextView tvTitleKindProduct;
        public KindProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewKindProduct = itemView.findViewById(R.id.imageViewKindProduct);
            tvTitleKindProduct = itemView.findViewById(R.id.tvTitleKindProduct);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tvTitleKindProduct.getText().toString().equals("Chuột máy tính")){
                        Intent intent = new Intent(itemView.getContext(), ListMouseProduct.class);
                        intent.putExtra("type","mouse");
                        itemView.getContext().startActivity(intent);
                    }else if (tvTitleKindProduct.getText().toString().equals("Đồng hồ")){
                        Intent intent = new Intent(itemView.getContext(),ListMouseProduct.class);
                        intent.putExtra("type","watch");
                        itemView.getContext().startActivity(intent);
                    }

                }
            });
        }
    }
}
