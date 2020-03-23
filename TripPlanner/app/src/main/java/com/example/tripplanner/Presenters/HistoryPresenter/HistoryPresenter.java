package com.example.tripplanner.Presenters.HistoryPresenter;

import com.example.tripplanner.Models.HistoryModel.HistoryContract;
import com.example.tripplanner.Models.HistoryModel.HistoryModel;
import com.example.tripplanner.POJOs.Trip;
import com.example.tripplanner.Views.HistoryView.History;

import java.util.ArrayList;

public class HistoryPresenter implements HistoryContract.IPresenter {

    HistoryContract.IView view;
    HistoryContract.IModel model;

    public HistoryPresenter(HistoryContract.IView view){
        this.view=view;
        this.model=new HistoryModel((History) view);
    }

    @Override
    public void handleHistory() {
        ArrayList<Trip> trips=model.getHistory("userName");
        view.renderHistory(trips);
    }
}
