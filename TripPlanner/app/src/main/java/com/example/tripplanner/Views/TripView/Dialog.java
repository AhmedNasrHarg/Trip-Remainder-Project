package com.example.tripplanner.Views.TripView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tripplanner.Models.DialogModel.DialogContract;
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
    String id;
    boolean chkService=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Log.i("nasor","create");
        presenter=new DialogPresenter(this);
         intent=getIntent();
        start = findViewById(R.id.start);
        cancel = findViewById(R.id.cancel);
        snooze = findViewById(R.id.snooze);
        if(savedInstanceState==null) {
            Log.i("nasor","null");
            endPoint = intent.getStringExtra("endPoint");
            reqCode = intent.getStringExtra("reqCode");
            id = intent.getStringExtra("id");
        }else{
            Log.i("nasor","not null");
            endPoint = savedInstanceState.getString("endPoint");
            reqCode = savedInstanceState.getString("reqCode");
            id = savedInstanceState.getString("id");
        }
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
                Dialog.this.finish();
                presenter.handleDoneTrip(id);
                if(chkService)
                    stopService(new Intent(v.getContext(),ForegroundService.class));
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                presenter.handleDoneTrip(id);
                if(chkService)
                    stopService(new Intent(v.getContext(),ForegroundService.class));
            }
        });
        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chkService){
                Intent intent=new Intent(v.getContext(), ForegroundService.class);
                intent.putExtra("reqCode",reqCode);
                startService(intent);
                }
                chkService=true;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("nasor","save");
        outState.putString("endPoint",endPoint);
        outState.putString("reqCode",reqCode);
        outState.putString("id",id);
    }

    public void openMap() {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q="+endPoint));       //[use langtiude and latitude]
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);


    }
}
