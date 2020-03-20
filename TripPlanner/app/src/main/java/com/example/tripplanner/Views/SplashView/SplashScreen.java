package com.example.tripplanner.Views.SplashView;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripplanner.Views.Login.Login;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}
