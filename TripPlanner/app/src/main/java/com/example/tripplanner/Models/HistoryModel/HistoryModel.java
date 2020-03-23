package com.example.tripplanner.Models.HistoryModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.tripplanner.POJOs.Trip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryModel implements HistoryContract.IModel {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("trips");
    ArrayList<Trip>trips=new ArrayList<>();
    HistoryContract.IView view;

    public HistoryModel(final HistoryContract.IView view){
//        myRef.child("test").setValue("test");
        this.view=view;
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                trips.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Trip curTrip=snapshot.getValue(Trip.class);
                    if(!curTrip.getStatus().equals("Upcoming")){
                        trips.add(curTrip);
                        Log.i("nnnnnnnnnn","mmmmmmmmmm");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText((Context) HistoryModel.this.view,"err",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public ArrayList<Trip> getHistory(String user) {
        return trips;
    }
}
