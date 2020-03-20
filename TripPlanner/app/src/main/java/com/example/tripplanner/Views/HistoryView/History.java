package com.example.tripplanner.Views.HistoryView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripplanner.Models.HistoryModel.HistoryContract;
import com.example.tripplanner.R;

public class History extends AppCompatActivity implements HistoryContract.IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }
}
