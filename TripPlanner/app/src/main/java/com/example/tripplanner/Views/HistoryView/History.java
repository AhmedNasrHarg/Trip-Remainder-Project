package com.example.tripplanner.Views.HistoryView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.Models.HistoryModel.HistoryContract;
import com.example.tripplanner.POJOs.Trip;
import com.example.tripplanner.POJOs.TripAdapter;
import com.example.tripplanner.Presenters.HistoryPresenter.HistoryPresenter;
import com.example.tripplanner.R;

import java.util.ArrayList;

public class History extends AppCompatActivity implements HistoryContract.IView, TripAdapter.OnTripListener {

    RecyclerView recyclerView;
    public TripAdapter arrayAdapter;
    RecyclerView.LayoutManager recyce;
    ArrayList<Trip> trips=new ArrayList<>();
    HistoryPresenter historyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyPresenter=new HistoryPresenter(this);
        historyPresenter.handleHistory();

        recyclerView=findViewById(R.id.historyRecyclerView);
        recyce = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(recyce);
        arrayAdapter=new TripAdapter(getApplicationContext(),R.layout.trip_row ,R.id.tripId,trips,this);
        recyclerView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        historyPresenter.handleHistory();
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void renderHistory(ArrayList<Trip> trips) {
        this.trips=trips;
    }

    @Override
    public void onTripClick(int position) {
        // here we navigate to details of current Trip
    }
}
