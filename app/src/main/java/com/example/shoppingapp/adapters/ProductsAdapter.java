package com.example.shoppingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.activities.ProductDetailedActivity;
import com.example.shoppingapp.model.ProductsModel;

import java.io.Serializable;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder>{

    Context context;
    List<ProductsModel> productsModels;

    public ProductsAdapter(Context context, List<ProductsModel> productModels) {
        this.context = context;
        this.productsModels = productModels;
    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.see_pop_products_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(productsModels.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(productsModels.get(position).getName());
        holder.rating.setText(productsModels.get(position).getRating());
        holder.price.setText(String.valueOf(productsModels.get(position).getPrice()) + " TL");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailedActivity.class);
                intent.putExtra("detail", (Serializable) productsModels.get(holder.getAdapterPosition()));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return productsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, rating,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.see_pop_products_img);
            name = itemView.findViewById(R.id.see_pop_name);
            rating = itemView.findViewById(R.id.see_pop_rating);
            price = itemView.findViewById(R.id.see_pop_price);


        }
    }
}
