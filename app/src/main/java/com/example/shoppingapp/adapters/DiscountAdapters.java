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
import com.example.shoppingapp.activities.ProductsActivity;
import com.example.shoppingapp.model.DiscountModel;

import java.util.List;

public class DiscountAdapters extends RecyclerView.Adapter<DiscountAdapters.ViewHolder> {

    Context context;
    List<DiscountModel> discountModels;

    public DiscountAdapters(Context context, List<DiscountModel> popularModelList) {
        this.context = context;
        this.discountModels = popularModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {

        Glide.with(context).load(discountModels.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(discountModels.get(position).getName());
        holder.rating.setText(discountModels.get(position).getRating());
        holder.discount.setText(discountModels.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("type", discountModels.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);
            }
        });


    }

    @Override

    public int getItemCount() {
        return discountModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name, rating, discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg = itemView.findViewById(R.id.ImgPop);
            name = itemView.findViewById(R.id.popname);
            rating = itemView.findViewById(R.id.popratingTxt);
            discount = itemView.findViewById(R.id.popdiscount);


        }
    }
}
