package com.example.tripplanner.Views.TripView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.example.tripplanner.Models.TripModel.TripContract;
import com.example.tripplanner.R;

import java.text.DateFormat;
import java.util.Calendar;

public class TripActivity extends AppCompatActivity implements TripContract.IView , DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener  {

    Button cal;
    Button time;
    TextView calDate;
    TextView timeTxt;
    Calendar c;
    NotificationHelper notifHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Add New Trip");

        notifHelper = new NotificationHelper(this );
        c = Calendar.getInstance();
        calDate = findViewById(R.id.calDate);
        time = findViewById(R.id.time);
        timeTxt = findViewById(R.id.timeText);
        cal = findViewById(R.id.calendar);
        time.setBackgroundResource(R.drawable.ala);
        cal.setBackgroundResource(R.drawable.calendar7);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DialogFragment datePicker = new com.example.tripplanner.Views.TripView.DatePickerFrag();
                datePicker.show(getSupportFragmentManager() , "date picker");
            }
        });


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFrag();
                timePicker.show(getSupportFragmentManager() , "Time picker");  //calls notifiction function
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
       // Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR , year);
        c.set(Calendar.MONTH , month);
        c.set(Calendar.DAY_OF_MONTH , dayOfMonth);
        String date = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        calDate.setText(date);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


        c.set(Calendar.MINUTE , minute);
        c.set(Calendar.HOUR_OF_DAY , hourOfDay);
        c.set(Calendar.SECOND , 0);
        timeTxt.setText(String.valueOf(hourOfDay)+ " : " + String.valueOf(minute));
        startAlarm(c);
    }




    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c) {
        AlarmManager alarmang = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pi =  PendingIntent.getBroadcast(this , 1 , intent , 0);
        alarmang.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
    }
}
