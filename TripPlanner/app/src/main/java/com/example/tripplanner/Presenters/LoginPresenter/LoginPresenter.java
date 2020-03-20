package com.example.tripplanner.Presenters.LoginPresenter;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.example.tripplanner.Models.LoginModel.LoginContract;
import com.example.tripplanner.Views.Login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements LoginContract.IPresenter {
    LoginContract.IView loginCont;
    private FirebaseAuth auth ;

    public LoginPresenter(FirebaseAuth auth) {
        this.auth = auth;
    }

    @Override
    public void performLogin(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            loginCont.loginValidations();
        } else {

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Login) loginCont, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                loginCont.loginSuccess();
                            } else {
                                loginCont.loginError();
                            }
                        }
                    });
        }
    }

    @Override
    public void checkLogin() {
        if (auth.getCurrentUser() != null)
            loginCont.isLogin(true);
        else
            loginCont.isLogin(false);
    }

}
