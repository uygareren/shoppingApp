package com.example.shoppingapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.model.FavoriteModel;
import com.example.shoppingapp.model.ProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetailedActivity extends AppCompatActivity {


    TextView quantity;
    int totalQuantity =1;
    int totalPrice = 0;

    ImageView detailedImg;
    TextView price,rating;
    Button addBtn;
    ImageView addItem,removeItem;
    String img_url;

    ProductsModel productsModel = null;



    FirebaseFirestore firestore;
    FirebaseAuth auth;


    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detailed);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof ProductsModel){
            productsModel = (ProductsModel) object;
        }



        quantity = findViewById(R.id.quantity);
        detailedImg = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);
        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        addBtn = findViewById(R.id.add_btn);
        img_url = String.valueOf(Glide.with(getApplicationContext()).load(productsModel.getImg_url()));



        if(productsModel!=null){
            Glide.with(getApplicationContext()).load(productsModel.getImg_url()).into(detailedImg);
            rating.setText(productsModel.getRating());
            price.setText(""+productsModel.getPrice());

            totalPrice =  productsModel.getPrice() * totalQuantity;

        }



        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedToBag();
            }
        });





        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity<10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice =productsModel.getPrice()+totalQuantity;

                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity>0){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice =productsModel.getPrice()+totalQuantity;

                }
            }
        });

    }



    private void addedToBag() {


        final HashMap<String,Object> bagMap = new HashMap<>();

        bagMap.put("productName",productsModel.getName());
        bagMap.put("productPrice",price.getText().toString());
        bagMap.put("totalQuantity",quantity.getText().toString());
        bagMap.put("totalPrice",totalPrice);


                firestore.collection("AddToBag").add(bagMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(ProductDetailedActivity.this,"Added to A Bag",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }


}