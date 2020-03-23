package com.example.tripplanner.Presenters.HomePresenter;

import com.example.tripplanner.Models.HomeModel.HomeContract;
import com.example.tripplanner.Models.HomeModel.HomeModel;
import com.example.tripplanner.POJOs.Trip;
import com.example.tripplanner.Views.HomeView.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements HomeContract.IPresenter {
    HomeContract.IView view;
    HomeContract.IModel model;
    public HomePresenter(HomeContract.IView view){
       this.view=view;
       model=new HomeModel((MainActivity) view);
    }

    @Override
    public void handleUpcomings() {
        ArrayList<Trip> trips=model.getUpcomings("userName");
        view.renderUpcomings(trips);
    }
}
