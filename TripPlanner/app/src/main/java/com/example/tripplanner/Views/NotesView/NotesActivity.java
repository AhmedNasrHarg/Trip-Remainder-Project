package com.example.tripplanner.Views.NotesView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tripplanner.Adapters.NotesAdapter;
import com.example.tripplanner.Adapters.TripAdapter;
import com.example.tripplanner.POJOs.Trip;
import com.example.tripplanner.R;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public NotesAdapter arrayAdapter;
    RecyclerView.LayoutManager recyce;
    ArrayList<String> notes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        notes.add("C/C++");
        notes.add("Android");
        notes.add("Java");
        recyclerView=findViewById(R.id.notesRecyclerView);
        recyce = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(recyce);
        arrayAdapter=new NotesAdapter(this,R.layout.note_row ,R.id.noteTextView,notes);
        recyclerView.setAdapter(arrayAdapter);
    }
}
