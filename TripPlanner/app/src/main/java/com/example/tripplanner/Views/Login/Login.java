package com.example.tripplanner.Views.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripplanner.Models.LoginModel.LoginContract;
import com.example.tripplanner.Presenters.LoginPresenter.LoginPresenter;
import com.example.tripplanner.R;
import com.example.tripplanner.Views.HomeView.MainActivity;
import com.example.tripplanner.Views.Register.Register;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements LoginContract.IView {
    EditText emailTxt;
    EditText passTxt;
    Button btnLogin;
    Button btnReg;
    Button btnGoogle;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient mGoogleSignInClient;
    private LoginPresenter presenter;

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
        presenter = new LoginPresenter(firebaseAuth,this);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTxt.getText().toString().trim();
                String password = passTxt.getText().toString().trim();
                presenter.performLogin(email, password);
                presenter.checkLogin();
            }
        });

    }

    @Override
    public void loginValidations() {
        Toast.makeText(Login.this, "Please Fill ALL Field", Toast.LENGTH_LONG).show();

    }

    @Override
    public void loginSuccess() {
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void loginError() {
        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT);
    }


    @Override
    public void isLogin(boolean isLogin) {
        if (isLogin == true) {
            Intent i = new Intent(Login.this,MainActivity.class);
            startActivity(i);
        } else {
            Log.d("sign","onAuthStateChanged:signed_out");
        }
    }
}
