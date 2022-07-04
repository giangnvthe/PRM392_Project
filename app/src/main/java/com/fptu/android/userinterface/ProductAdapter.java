package com.fptu.android.userinterface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.UserViewHolder>{

    private List<Product> mListProduct;
    private Context mContext;

    public ProductAdapter(List<Product> mListProduct) {
        this.mListProduct = mListProduct;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Product product = mListProduct.get(position);
        if(product == null){
            return;
        }
        holder.tvID.setText("ID: " + product.getId());
        holder.tvName.setText("Name:" + product.getName());
        holder.tvPrice.setText("Price: " +product.getPrice());
        Glide.with(holder.imageProduct.getContext()).load((product.getSurl())).into(holder.imageProduct);
    }

    @Override
    public int getItemCount() {
        if(mListProduct != null){
            return mListProduct.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView tvID;
        private TextView tvName;
        private TextView tvPrice;
        private ImageView imageProduct;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            imageProduct = itemView.findViewById(R.id.imageProduct);
        }
    }
}

