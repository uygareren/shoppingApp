package com.example.shoppingapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.shoppingapp.R;
import com.example.shoppingapp.adapters.ProductsAdapter;
import com.example.shoppingapp.model.ProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    ProductsAdapter productsAdapter;
    List<ProductsModel> productsModelList;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_popular);


        firestore = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.see_pop_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productsModelList = new ArrayList<>();
        productsAdapter = new ProductsAdapter(this, productsModelList);
        recyclerView.setAdapter(productsAdapter);



        //Electronics

        if(type != null && type.equalsIgnoreCase("electronics")){
            firestore.collection("AllProducts").whereEqualTo("type", "electronics").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ProductsModel seePopularModel = documentSnapshot.toObject(ProductsModel.class);
                        productsModelList.add(seePopularModel);
                        productsAdapter.notifyDataSetChanged();
                    }

                }
            });
        }


        // Supermarket

        if(type != null && type.equalsIgnoreCase("supermarket")){
            firestore.collection("AllProducts").whereEqualTo("type", "supermarket").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ProductsModel seePopularModel = documentSnapshot.toObject(ProductsModel.class);
                        productsModelList.add(seePopularModel);
                        productsAdapter.notifyDataSetChanged();
                    }

                }
            });
        }

        // Book

        if(type != null && type.equalsIgnoreCase("book")){
            firestore.collection("AllProducts").whereEqualTo("type", "book").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ProductsModel seePopularModel = documentSnapshot.toObject(ProductsModel.class);
                        productsModelList.add(seePopularModel);
                        productsAdapter.notifyDataSetChanged();
                    }

                }
            });
        }

        // Cosmetic

        if(type != null && type.equalsIgnoreCase("cosmetic")){
            firestore.collection("AllProducts").whereEqualTo("type", "cosmetic").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ProductsModel seePopularModel = documentSnapshot.toObject(ProductsModel.class);
                        productsModelList.add(seePopularModel);
                        productsAdapter.notifyDataSetChanged();
                    }

                }
            });
        }

        // Accessory

        if(type != null && type.equalsIgnoreCase("accessory")){
            firestore.collection("AllProducts").whereEqualTo("type", "accessory").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ProductsModel seePopularModel = documentSnapshot.toObject(ProductsModel.class);
                        productsModelList.add(seePopularModel);
                        productsAdapter.notifyDataSetChanged();
                    }

                }
            });
        }


        // fashion

        if(type != null && type.equalsIgnoreCase("fashion")){
            firestore.collection("AllProducts").whereEqualTo("type", "fashion").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ProductsModel seePopularModel = documentSnapshot.toObject(ProductsModel.class);
                        productsModelList.add(seePopularModel);
                        productsAdapter.notifyDataSetChanged();
                    }

                }
            });
        }





    }
}