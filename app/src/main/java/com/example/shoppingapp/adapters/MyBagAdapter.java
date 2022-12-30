package com.example.shoppingapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.model.MyBagModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyBagAdapter extends RecyclerView.Adapter<MyBagAdapter.ViewHolder> {

    Context context;
    List<MyBagModel> bagList;  //sepetimdekilerin listesi
    int totalPrice = 0;

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    public MyBagAdapter(Context context, List<MyBagModel> bagList) {
        this.context = context;
        this.bagList = bagList;

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_bag_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(bagList.get(position).getImg_url()).into(holder.Product_img);
        holder.name.setText(bagList.get(position).getProductName());
        holder.price.setText(bagList.get(position).getProductPrice());
        holder.quantity.setText(bagList.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(bagList.get(position).getTotalPrice()));

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        firestore.collection("AddToBag")
                        .document(bagList.get(position).getDocumentId())
                        .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    bagList.remove(bagList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context,"Item Deleted",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });





        totalPrice = totalPrice + bagList.get(position).getTotalPrice();
        Intent intent =  new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return bagList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,price,quantity,totalPrice;
        ImageView deleteItem,Product_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Product_img = itemView.findViewById(R.id.product_img);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);

            quantity = itemView.findViewById(R.id.total_quantity);
            totalPrice = itemView.findViewById(R.id.total_price);
            deleteItem = itemView.findViewById(R.id.delete_btn);


        }
    }
}
