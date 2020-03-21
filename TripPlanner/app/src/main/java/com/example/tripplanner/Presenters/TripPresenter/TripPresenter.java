package com.example.tripplanner.Presenters.TripPresenter;

import com.example.tripplanner.Models.TripModel.TripContract;
import com.example.tripplanner.Models.TripModel.TripModel;
import com.example.tripplanner.POJOs.Trip;

public class TripPresenter implements TripContract.IPresenter {
    TripContract.IView view;
    TripContract.IModel model;
    public TripPresenter(TripContract.IView  view){
        this.view=view;
        model=new TripModel();
    }
    public void addNewTrip(Trip trip){
        // add it to the firebase
        model.addNewTrip(trip);
        view.addedNewTrip();
    }
}
