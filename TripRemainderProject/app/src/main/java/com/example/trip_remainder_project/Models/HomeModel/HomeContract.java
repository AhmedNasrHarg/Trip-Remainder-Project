package com.example.trip_remainder_project.Models.HomeModel;

import com.example.trip_remainder_project.POJOs.Trip;

import java.util.List;

public class HomeContract {
    public interface IView{
        public void renderUpcomings(List<Trip> trips);
    }
    public interface IModel{
        public List<Trip> getUpcomings(String user);
    }
    public interface IPresenter{
        public void handleUpcomings();
    }
}
