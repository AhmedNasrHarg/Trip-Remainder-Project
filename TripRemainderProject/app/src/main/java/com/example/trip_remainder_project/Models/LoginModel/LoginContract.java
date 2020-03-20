package com.example.trip_remainder_project.Models.LoginModel;

public class LoginContract {

    public  interface IView{
        void loginValidations();
        void loginSuccess();
        void loginError();
        void isLogin(boolean isLogin);
    }

    public interface IPresenter{
        void performLogin(String email,String password);
        void checkLogin();
    }

    public interface IModel {

    }
}
