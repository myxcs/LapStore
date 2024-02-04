package com.example.lapstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

public class SplashScreen extends AppCompatActivity {
     Button Login, Register;

    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //adding button
        Login = findViewById(R.id.btn_login);
        Register = findViewById(R.id.btn_signup);
        database = FirebaseDatabase.getInstance();
        database.getReference().child("Users");


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreen.this, LoginScreen.class);
                startActivity(intent);
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreen.this, RegisterScreen.class);
                startActivity(intent);
            }
        });
    }
}