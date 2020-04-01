package com.example.tripplanner.Views.SplashView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripplanner.Views.HomeView.MainActivity;
import com.example.tripplanner.Views.Login.Login;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("MyLogin.txt", Context.MODE_PRIVATE);
        Boolean loginCheck = sharedPreferences.getBoolean("FirstLogin", false);
        String user = sharedPreferences.getString("user", "");
        if (loginCheck){

            Intent  intent = new Intent(getApplicationContext(), MainActivity.class);   // add user
            intent.putExtra("user",user);
            startActivity(intent);
        }else{
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        }
        finish();
    }
}
