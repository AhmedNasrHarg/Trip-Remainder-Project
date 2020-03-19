package com.example.trip_remainder_project.Presenters.HomePresenter;

import android.icu.util.ValueIterator;
import android.view.View;

import com.example.trip_remainder_project.Models.HomeModel.HomeContract;
import com.example.trip_remainder_project.Models.HomeModel.HomeModel;
import com.example.trip_remainder_project.POJOs.Trip;

import java.util.List;

public class HomePresenter implements HomeContract.IPresenter {
    HomeContract.IView view;
    HomeContract.IModel model;
    public HomePresenter(HomeContract.IView view){
       this.view=view;
       model=new HomeModel();
    }

    @Override
    public void handleUpcomings() {
        List<Trip> trips=model.getUpcomings("userName");
        view.renderUpcomings(trips);
    }
}
