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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements LoginContract.IView {
    EditText emailTxt;
    EditText passTxt;
    Button btnLogin;
    Button btnReg;
    SignInButton signInButton ;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient mGoogleSignInClient;
    private LoginPresenter presenter;
    GoogleSignInAccount acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTxt = findViewById(R.id.emailTxt);
        passTxt = findViewById(R.id.passTxt);
        btnLogin = findViewById(R.id.btnLogin);
        btnReg = findViewById(R.id.btnReg);
        signInButton = findViewById(R.id.btnGoogle);
        firebaseAuth = FirebaseAuth.getInstance();
        presenter = new LoginPresenter(firebaseAuth,this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient  = GoogleSignIn.getClient(this,gso);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 1 );

            }
        });


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
                //        presenter.FirebaseGoogleAuth(acc);
            }
        });


    }

    @Override
    public void loginValidations() {
        Toast.makeText(Login.this, "Please Fill ALL Field", Toast.LENGTH_LONG).show();

    }

    @Override
    public void loginSuccess() {
        Toast.makeText(Login.this, "Login success", Toast.LENGTH_SHORT);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 ){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        }
    }

    @Override
    public void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount acc = task.getResult(ApiException.class);
            Intent i = new Intent(Login.this,MainActivity.class);
            startActivity(i);
            Toast.makeText(Login.this , "Sign In Susseful", Toast.LENGTH_LONG).show();
            presenter.FirebaseGoogleAuth(acc);
        } catch (ApiException e) {
            Toast.makeText(Login.this , "Sign In Fail", Toast.LENGTH_LONG).show();
            presenter.FirebaseGoogleAuth(null);


        }
    }

    @Override
    public void updateUI(FirebaseUser fUser) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        String name = account.getDisplayName();
        String email = account.getEmail();
        emailTxt.setText(email);

    }


}
