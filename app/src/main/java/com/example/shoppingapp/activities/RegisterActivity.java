package com.example.shoppingapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.R;
import com.example.shoppingapp.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    TextView Name, Email, Password, SignInTxt;
    Button Register;
    FirebaseAuth auth;
    FirebaseDatabase database;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name = findViewById(R.id.SignUpName);
        Email = findViewById(R.id.SignUpEmail);
        Password = findViewById(R.id.signUpPassword);
        SignInTxt = findViewById(R.id.TxtSignin);
        Register = findViewById(R.id.BtnSignUp);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        SignInTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createUser();

            }
        });

    }

    private void createUser() {

        String username = Name.getText().toString();
        String useremail = Email.getText().toString();
        String userpassword = Password.getText().toString();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "User name is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(useremail)){
            Toast.makeText(this, "User e-mail is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userpassword)){
            Toast.makeText(this, "User password is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userpassword.length() < 6){
            Toast.makeText(this, "Password cannot be less than 6", Toast.LENGTH_SHORT).show();
            return;
        }

        // CREATİNG USER..

        auth.createUserWithEmailAndPassword(useremail, userpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            UserModel userModel = new UserModel(username, useremail, userpassword);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(userModel);


                            Toast.makeText(RegisterActivity.this, "Registration is Succesfull", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }
}
