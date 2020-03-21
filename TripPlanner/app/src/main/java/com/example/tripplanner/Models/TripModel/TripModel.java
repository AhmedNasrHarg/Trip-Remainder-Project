package com.example.tripplanner.Models.TripModel;

import com.example.tripplanner.POJOs.Trip;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TripModel implements TripContract.IModel {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("trips");

    @Override
    public void addNewTrip(Trip trip) {
        myRef.child(myRef.push().getKey()).setValue(trip);
    }
}
