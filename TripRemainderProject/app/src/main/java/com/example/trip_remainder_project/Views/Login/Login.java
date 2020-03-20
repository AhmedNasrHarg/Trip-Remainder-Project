package com.example.trip_remainder_project.Views.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.trip_remainder_project.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText emailTxt;
    EditText passTxt;
    Button btnLogin;
    Button btnReg;
    Button btnGoogle;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient mGoogleSignInClient ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTxt = findViewById(R.id.emailTxt);
        passTxt = findViewById(R.id.passTxt);
        btnLogin = findViewById(R.id.btnLogin);
        btnReg = findViewById(R.id.btnReg);
        btnGoogle = findViewById(R.id.btnGoogle);
        firebaseAuth = FirebaseAuth.getInstance();

    }
}
