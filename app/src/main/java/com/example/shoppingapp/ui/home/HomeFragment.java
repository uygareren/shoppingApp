package com.example.shoppingapp.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.R;
import com.example.shoppingapp.activities.ProfileActivity;
import com.example.shoppingapp.adapters.HomeAdapter;
import com.example.shoppingapp.adapters.DiscountAdapters;
import com.example.shoppingapp.adapters.ProductsAdapter;
import com.example.shoppingapp.model.HomeCategory;
import com.example.shoppingapp.model.DiscountModel;
import com.example.shoppingapp.model.ProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {




    RecyclerView discountRec, homeCatRec; // Recyclerview attributeleri.
    FirebaseFirestore db; // Firebase attribute

    ImageView GoProfile;


    ScrollView scrollView;


    //POPULAR SECTİON
    List<DiscountModel> discountModels; // Discount recycler view için model sınıfından liste oluşturduk.
    DiscountAdapters discountAdapters; // Discount recycler view için Adapterünü tanımladık.


    // SEARCH SECTİON




    //HOME CATEGORY SECTİON

    List<HomeCategory> categoryList;  // Home categories recycler view için model sınıfından liste oluşturduk.
    HomeAdapter homeAdapter;            // Home categories recycler view için Adapterünü tanımladık.





    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        db = FirebaseFirestore.getInstance();  // Firestore veritabanı içerisinde yer alan collection kısmını elde etmek için tanımladık.

        discountRec = root.findViewById(R.id.pop_rec);  // discount kısmının recyclerview id'si ile eşleştirdik
        homeCatRec = root.findViewById(R.id.category_rec);  // home category kısmının recyclerview id'si ile eşleştirdik

        scrollView = root.findViewById(R.id.scroll_view);

        scrollView.setVisibility(View.GONE);

        GoProfile = root.findViewById(R.id.goProfile);

        GoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });


        // POPULAR İTEMS
        discountRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));
        //recycler viewin özelliklerini girdik

        discountModels = new ArrayList<>(); // içine atacağımız ürünlerin listesi
        discountAdapters = new DiscountAdapters(getActivity(), discountModels); //adaptere yolladık
        discountRec.setAdapter(discountAdapters); // adapterle bağdaştırdık




         /*
         Aşağıdaki kod Firebaseden "PopularProducts" koleksiyonunu alıyor.
           İçindekiler DiscountModel sınıfından nesnelere dönüştürülerek discountModels ArrayList'ine eklenir.
           Daha sonra, discountAdapters güncellenir. Scrollview gözükür.
          */
        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DiscountModel popularModel = document.toObject(DiscountModel.class);
                                discountModels.add(popularModel);
                                discountAdapters.notifyDataSetChanged();
                                scrollView.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Toast.makeText(getActivity(), "error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        // HOME CATEGORY İTEMS

        homeCatRec.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
        categoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(), categoryList);
        homeCatRec.setAdapter(homeAdapter);

        /*
         Aşağıdaki kod Firebaseden "HomeCategory" koleksiyonunu alıyor.
           İçindekiler HomeCategory sınıfından nesnelere dönüştürülerek categoryList ArrayList'ine eklenir.
           Daha sonra, HomeAdapter güncellenir.
          */

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                categoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });







        return root;
    }

}