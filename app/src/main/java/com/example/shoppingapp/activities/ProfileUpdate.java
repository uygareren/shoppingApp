package com.example.shoppingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoppingapp.R;

public class ProfileUpdate extends AppCompatActivity {

    TextView UptName, UptSurname, UptPhone, UptEmail;
    Button UpdateBtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        UptName = findViewById(R.id.profile_name);
        UptSurname = findViewById(R.id.profile_surname);
        UptPhone = findViewById(R.id.profile_phonenumber);
        UptEmail = findViewById(R.id.profile_email);
        UpdateBtn = findViewById(R.id.update);

        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = UptName.getText().toString();
                String Surname = UptSurname.getText().toString();
                String Phone = UptPhone.getText().toString();
                String Email = UptEmail.getText().toString();

                Intent intent = new Intent(ProfileUpdate.this, ProfileActivity.class);
                intent.putExtra("name", Name);
                intent.putExtra("surname", Surname);
                intent.putExtra("phone", Phone);
                intent.putExtra("email", Email);
                startActivity(intent);

            }
        });


    }

}