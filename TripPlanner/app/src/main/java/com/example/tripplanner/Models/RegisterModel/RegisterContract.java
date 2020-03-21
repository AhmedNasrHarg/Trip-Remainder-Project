package com.example.tripplanner.Models.RegisterModel;

public class RegisterContract {
    public  interface IView{
        void regValidations();
        void regSuccess();
        void regError();
        void isreg(boolean isReg);
    }

    public interface IPresenter{
        void performReg(String email,String password,String confirmPassword);

    }

    public interface IModel {

    }
}
