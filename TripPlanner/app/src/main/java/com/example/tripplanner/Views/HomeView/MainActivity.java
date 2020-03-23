package com.example.tripplanner.Views.HomeView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.Models.HomeModel.HomeContract;
import com.example.tripplanner.POJOs.Trip;
import com.example.tripplanner.POJOs.TripAdapter;
import com.example.tripplanner.Presenters.HomePresenter.HomePresenter;
import com.example.tripplanner.R;
import com.example.tripplanner.Views.HistoryView.History;
import com.example.tripplanner.Views.TripView.TripActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeContract.IView {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    HomePresenter homePresenter;
    
    RecyclerView recyclerView;
    TripAdapter arrayAdapter;
    RecyclerView.LayoutManager recyce;
    ArrayList<Trip> trips=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // here is presenter handling
        homePresenter=new HomePresenter(this);
        homePresenter.handleUpcomings();

        Toolbar toolbar=findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        NavigationView navigationView=findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if(id==R.id.upcomings){
                    menuItem.setCheckable(true);
                    menuItem.setChecked(true);
                }else if(id == R.id.history){
                    menuItem.setCheckable(true);
                    menuItem.setChecked(true);
                    Intent intent=new Intent(getApplicationContext(), History.class);
                    startActivity(intent);
                }else if(id==R.id.logout){
                    menuItem.setCheckable(true);
                    menuItem.setChecked(true);
                    Toast.makeText(getApplicationContext(),"logout",Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        recyclerView=findViewById(R.id.recyclerView);
        recyce = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(recyce);
        arrayAdapter=new TripAdapter(getApplicationContext(),R.layout.trip_row ,R.id.tripId,trips);
        recyclerView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // only add the upcoming trips, if the status is upcoming
        homePresenter.handleUpcomings();
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void renderUpcomings(ArrayList<Trip> trips) {
        // render here the trips on the recycler listView
        this.trips=trips;

    }

    public void addNewTrip(View view) {
        // startActivityForResult
        Intent intent=new Intent(getApplicationContext(), TripActivity.class);
        startActivity(intent);
    }


}
