package com.example.tripplanner.Models.LoginModel;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

public class LoginContract {

    public  interface IView{
        void loginValidations();
        void loginSuccess();
        void loginError();
        void isLogin(boolean isLogin);
        void onActivityResult(int requestCode, int resultCode, Intent data);
        void handleSignInResult(Task<GoogleSignInAccount> task);
        void updateUI(FirebaseUser fUser);

    }

    public interface IPresenter{
        void performLogin(String email,String password);
        void checkLogin();
        void FirebaseGoogleAuth(GoogleSignInAccount acc);
    }

    public interface IModel {

    }
}
