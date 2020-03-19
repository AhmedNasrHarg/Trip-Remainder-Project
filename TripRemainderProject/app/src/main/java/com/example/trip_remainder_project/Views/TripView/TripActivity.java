package com.example.trip_remainder_project.Views.TripView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trip_remainder_project.Models.TripModel.TripContract;
import com.example.trip_remainder_project.R;

public class TripActivity extends AppCompatActivity implements TripContract.IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
    }
}
