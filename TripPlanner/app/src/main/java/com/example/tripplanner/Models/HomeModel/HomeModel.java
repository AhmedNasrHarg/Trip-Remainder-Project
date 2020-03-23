package com.example.tripplanner.Models.HomeModel;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.tripplanner.POJOs.Trip;
import com.example.tripplanner.Views.HomeView.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeModel implements HomeContract.IModel {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("trips");
    ArrayList<Trip>trips=new ArrayList<>();
    MainActivity view;
    public HomeModel(final MainActivity view){
        this.view=view;
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                trips.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Trip curTrip=snapshot.getValue(Trip.class);
                    if(curTrip.getStatus().equals("Upcoming"))
                        trips.add(curTrip);
                }
                // we must notify that we have loaded all trips coz it is running in a different thread, so we can see
                // trips on after activity loaded not else
                HomeModel.this.view.arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText((Context) HomeModel.this.view,"err",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public ArrayList<Trip> getUpcomings(String user){
        return trips;
    }
}
