package com.example.tripplanner.Presenters.HomePresenter;

import com.example.tripplanner.Models.HomeModel.HomeContract;
import com.example.tripplanner.Models.HomeModel.HomeModel;
import com.example.tripplanner.POJOs.Trip;
import com.example.tripplanner.Views.HomeView.MainActivity;

import java.util.ArrayList;

public class HomePresenter implements HomeContract.IPresenter {
    HomeContract.IView view;
    HomeContract.IModel model;
    String user;
    public HomePresenter(HomeContract.IView view,String user){
       this.view=view;
       this.user=user;
       model=new HomeModel((MainActivity) view,user);
    }

    @Override
    public void handleUpcomings(String user) {
        ArrayList<Trip> trips=model.getUpcomings(user);
        view.renderUpcomings(trips);
    }
}
