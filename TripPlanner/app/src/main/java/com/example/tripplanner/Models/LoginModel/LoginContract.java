package com.example.tripplanner.Models.LoginModel;

public class LoginContract {

    public  interface IView{
        void loginValidations();
        void loginSuccess();
        void loginError();
        void checkCurrentUser();
        void isLogin(boolean isLogin);
    }

    public interface IPresenter{
        void performLogin(String email,String password);
        void checkLogin();
    }

    public interface IModel {

    }
}
