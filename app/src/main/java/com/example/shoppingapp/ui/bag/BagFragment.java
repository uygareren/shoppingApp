package com.example.shoppingapp.ui.bag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.R;
import com.example.shoppingapp.activities.PlacedOrderActivity;
import com.example.shoppingapp.adapters.MyBagAdapter;
import com.example.shoppingapp.model.MyBagModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class BagFragment extends Fragment {

    FirebaseFirestore db; // Firebase attribute
    FirebaseAuth auth;

    TextView overTotalAmount;

    RecyclerView recyclerView;
    MyBagAdapter myBagAdapter;
    List<MyBagModel> myBagModels; //sepetimdekilerin listesi
    Button buyNow; //satın alma tuşu



        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            // Inflate the layout for this fragment
            View root= inflater.inflate(R.layout.fragment_bag, container, false);

            db = FirebaseFirestore.getInstance();
            auth = FirebaseAuth.getInstance();
            recyclerView = root.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            buyNow = root.findViewById(R.id.buy_now);
            overTotalAmount =root.findViewById(R.id.textview6);

            //Bu kod, getActivity() metodu ile çağrılan bir aktivite içinde çalışan bir LocalBroadcastManager nesnesini oluşturmaktadır.
            //Sonra, bu kod parçasında mMessageReceiver adlı bir BroadcastReceiver nesnesi "MyTotalAmount" adlı bir IntentFilter ile register edilmektedir.
            // Bu sayede, "MyTotalAmount" adlı bir Intent çağrıldığında mMessageReceiver nesnesine iletilecektir.
            // Bu nesne, çağrılan Intent ile birlikte gönderilen verileri alarak, bu verilere göre işlemler yapabilir.
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));

            myBagModels = new ArrayList<>();
            myBagAdapter = new MyBagAdapter(getActivity(), myBagModels);

            recyclerView.setAdapter(myBagAdapter);


                    db.collection("AddToBag")
                            .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                    String documentID = documentSnapshot.getId();

                                    MyBagModel cartModel = documentSnapshot.toObject(MyBagModel.class);

                                    cartModel.setDocumentId(documentID);
                                    myBagModels.add(cartModel);
                                    myBagAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });

            buyNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), PlacedOrderActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity(), "Your order has been placed", Toast.LENGTH_SHORT).show();

                }
            });

            return root;


        }


        public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int totalBill = intent.getIntExtra("totalAmount",0);
                overTotalAmount.setText("Total Bill: " + totalBill);
            }
        };



}
