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
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.example.tripplanner.Models.TripModel.TripContract;
import com.example.tripplanner.POJOs.Trip;
import com.example.tripplanner.Presenters.TripPresenter.TripPresenter;
import com.example.tripplanner.R;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class TripActivity extends AppCompatActivity implements TripContract.IView , DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener {

    Button cal;
    Button time;
    TextView calDate;
    TextView timeTxt;
    Calendar calendar;


    TripPresenter tripPresenter;
    Switch type;
    EditText tripName;
    EditText startPoint;
    EditText endPoint;
    String toggleCheck="oneWay";
//    double longtiude;
//    double latitude;
     double endLatitude;
    double endLongtude;
    double startLatitude;
    double startLongtude;
    String thePlaceId;
     String startPlaceName;
     String endPlaceName;
    String purpose="";
    Trip trip;
    static int reqCode = 0;

    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Add New Trip");
        tripPresenter=new TripPresenter(this);

        //ids
        addBtn=findViewById(R.id.addTripBtn);
        type=findViewById(R.id.switch1);
        tripName=findViewById(R.id.tripName);
//        startPoint=findViewById(R.id.startPoint);
//        endPoint=findViewById(R.id.endPoint);
        calDate = findViewById(R.id.calDate);
        time = findViewById(R.id.time);
        timeTxt = findViewById(R.id.timeText);
        cal = findViewById(R.id.calendar);
        time.setBackgroundResource(R.drawable.ala);
        cal.setBackgroundResource(R.drawable.calendar7);
       // reqCode =0;

        // to check if am coming from add new trip or edit a trip
        Intent intent =getIntent();
        purpose=intent.getExtras().getString("purpose");
        if(purpose.equals("editTrip")) {
            trip = (Trip) intent.getExtras().getSerializable("curTrip");
            getSupportActionBar().setTitle("Edit Trip");
            addBtn.setText("Save");
            type.setChecked(trip.getTripType().equals("round"));
            tripName.setText(trip.getTripName());
            startPoint.setText(trip.getStartPoint());
            endPoint.setText(trip.getEndPoint());
            calDate.setText(trip.getTripDate());
            timeTxt.setText(trip.getTripTime());
        }else{

        }

       type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if(isChecked)
                    toggleCheck="round";
                else
                    toggleCheck="oneWay";
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(tripName.getText().toString().length()>0&&startPoint.getText().toString().length()>0&&endPoint.getText().toString().length()>0
                && calDate.getText().toString().length()>0&&timeTxt.getText().toString().length()>0){
                    startAlarm(calendar);
//                    Trip curTrip=new Trip(tripName.getText().toString(),startPoint.getText().toString(),endPoint.getText().toString()
//                    ,calDate.getText().toString(),timeTxt.getText().toString(),toggleCheck,"Upcoming",longtiude,latitude);
//                    curTrip.addNewNote("Java");
//                    tripPresenter.addNewTrip(curTrip);
                }else{
                    Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

       // notifHelper = new NotificationHelper(this );
        calendar = Calendar.getInstance();

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

        //Auto Complete fragments
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyAlhcnF8gOLFVzwWJD2Gk0wR0EFVmvMf88");
        }
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        AutocompleteSupportFragment autocompleteFragment2 = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment2);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                startLatitude = place.getLatLng().latitude;
                startLongtude = place.getLatLng().longitude;
                startPlaceName = place.getName().toString();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
            }
        });

        autocompleteFragment2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                endLatitude = place.getLatLng().latitude;
                endLongtude = place.getLatLng().longitude;
                endPlaceName = place.getName().toString();
                thePlaceId = place.getId();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR , year);
        calendar.set(Calendar.MONTH , month);
        calendar.set(Calendar.DAY_OF_MONTH , dayOfMonth);
        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        calDate.setText(date);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


        calendar.set(Calendar.MINUTE , minute);
        calendar.set(Calendar.HOUR_OF_DAY , hourOfDay);
        calendar.set(Calendar.SECOND , 0);
        timeTxt.setText(String.valueOf(hourOfDay)+ " : " + String.valueOf(minute));
    //    startAlarm(c);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c) {
        //reqCode++;
        AlarmManager alarmang = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, ReminderBroadcast.class);
        PendingIntent pi =  PendingIntent.getBroadcast(this , reqCode++ , intent , 0);
        alarmang.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
    }

    @Override
    public void addedNewTrip() {
        finish();
    }
}
