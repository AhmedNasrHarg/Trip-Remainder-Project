package com.example.tripplanner.Models.TripModel;

import androidx.annotation.NonNull;

import com.example.tripplanner.Models.HomeModel.HomeModel;
import com.example.tripplanner.POJOs.Trip;
import com.example.tripplanner.Views.HomeView.MainActivity;
import com.example.tripplanner.Views.TripView.TripActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TripModel implements TripContract.IModel {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("trips");
    ArrayList<Trip> trips=new ArrayList<>();
    TripActivity view;
    public TripModel(TripActivity view){
        this.view=view;
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                trips.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Trip curTrip=snapshot.getValue(Trip.class);
                    if(curTrip.getStatus().equals("Upcoming"))
                        trips.add(curTrip);
//                    TripModel.this.view.arrayAdapter.notifyDataSetChanged();
                }
                // we must notify that we have loaded all trips coz it is running in a different thread, so we can see
                // trips on after activity loaded not else
//                TripModel.this.view.arrayAdapter.notifyDataSetChanged();
//                TripModel.this.view.finish(); do not do this here coz listener is called all time once you enter the activity
                // so it will always not appear
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void addNewTrip(Trip trip) {
        String id=myRef.push().getKey();
        trip.setId(id);
        myRef.child(id).setValue(trip);
    }
}
