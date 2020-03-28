package com.example.tripplanner.Views.TripView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tripplanner.R;

public class Dialog extends AppCompatActivity {

    Button start;
    Button cancel;
    Button snooze;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        start = findViewById(R.id.start);
        cancel = findViewById(R.id.cancel);
        snooze = findViewById(R.id.snooze);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(v.getContext(), ForegroundService.class));
                openMap();

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });



        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startService(new Intent(v.getContext(), ForegroundService.class));

            }
        });


    }

    public void openMap()
    {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=1+محمود+سلامة،+كوم+الدكة+غرب،+العطارين،+الإسكندرية"));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);


    }
}
