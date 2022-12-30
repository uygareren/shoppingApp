package com.example.shoppingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.R;
import com.example.shoppingapp.ui.home.HomeFragment;

public class ProfileActivity extends AppCompatActivity {

    TextView name, surname, phone, email;
    Button ProfileUpdateBtn, ProfileSettingsBtn, LogoutBtn;
    ImageView goHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        ProfileUpdateBtn = findViewById(R.id.profileUpdateBtn);
        LogoutBtn = findViewById(R.id.logoutBtn);
        goHome = findViewById(R.id.gobackHome);



        ProfileUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivity.this, ProfileUpdate.class);
                startActivity(intent);

            }
        });


        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String Name = intent.getStringExtra("name");
        String Surname = intent.getStringExtra("surname");
        String Phone = intent.getStringExtra("phone");
        String Email = intent.getStringExtra("email");

        name.setText(Name);
        surname.setText(Surname);
        phone.setText(Phone);
        email.setText(Email);

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(ProfileActivity.this, "Log out Succesfullt", Toast.LENGTH_SHORT).show();
            }
        });




    }
}