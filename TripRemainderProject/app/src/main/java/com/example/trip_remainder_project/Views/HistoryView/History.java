package com.example.trip_remainder_project.Views.HistoryView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trip_remainder_project.Models.HistoryModel.HistoryContract;
import com.example.trip_remainder_project.R;

public class History extends AppCompatActivity implements HistoryContract.IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }
}
