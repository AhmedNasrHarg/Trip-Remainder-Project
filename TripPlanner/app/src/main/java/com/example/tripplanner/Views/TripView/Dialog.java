package com.example.tripplanner.Views.TripView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tripplanner.Models.DialogModel.DialogContract;
import com.example.tripplanner.POJOs.Trip;
import com.example.tripplanner.Presenters.DialogPresenter.DialogPresenter;
import com.example.tripplanner.R;

public class Dialog extends AppCompatActivity implements DialogContract.IView {

    Button start;
    Button cancel;
    Button snooze;
    Intent intent;
    DialogContract.IPresenter presenter;
    String endPoint;
    String reqCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        presenter=new DialogPresenter(this);
         intent=getIntent();
        start = findViewById(R.id.start);
        cancel = findViewById(R.id.cancel);
        snooze = findViewById(R.id.snooze);

         endPoint=intent.getStringExtra("endPoint");
         reqCode=intent.getStringExtra("reqCode");
        final String id=intent.getStringExtra("id");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send trip to Foreground using intent to update reqCode
                Intent intent=new Intent(v.getContext(), ForegroundService.class);
                intent.putExtra("reqCode",reqCode);
                startService(intent);
                openMap();
                presenter.handleDoneTrip(id);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                presenter.handleDoneTrip(id);
            }
        });



        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(v.getContext(), ForegroundService.class);
                intent.putExtra("reqCode",reqCode);
                startService(intent);

            }
        });


    }
    public void openMap() {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q="+endPoint));       //[use langtiude and latitude]
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);


    }
}
