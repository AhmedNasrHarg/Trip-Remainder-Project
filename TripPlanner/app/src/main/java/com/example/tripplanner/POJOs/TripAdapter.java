package com.example.tripplanner.POJOs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.R;

import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<Trip> items;
    @NonNull
    @Override
    public TripAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row=null;
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row=layoutInflater.inflate(R.layout.trip_row,parent,false);
        ViewHolder holder=new ViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TripAdapter.ViewHolder holder, int position) {
        holder.date.setText(items.get(position).getTripDate());
        holder.time.setText(items.get(position).getTripTime());
        holder.name.setText(items.get(position).getTripName());
        holder.src.setText(items.get(position).getStartPoint());
        holder.dest.setText(items.get(position).getEndPoint());
        holder.type.setText(items.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView date;
        public TextView time;
        public TextView name;
        public TextView src;
        public TextView dest;
        public TextView type;
        public View layout;
        public void showNotes(View view) {
            Log.i("nasr","hi");
        }
        public ViewHolder(View v){
            super(v);
            layout=v;
            Button noteBtn=v.findViewById(R.id.noteId);
            noteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                showNotes(v);
                }
            });
            date=v.findViewById(R.id.dateId);   // take care ids from custom row
            time=v.findViewById(R.id.timeId);
            name=v.findViewById(R.id.tripId);   // take care ids from custom row
            src=v.findViewById(R.id.srcId);
            dest=v.findViewById(R.id.destId);   // take care ids from custom row
            type=v.findViewById(R.id.statusId);
        }

    }

    public TripAdapter(@NonNull Context context, int resource, int textViewResourceId, List items) {
        this.context=context;
        this.items= (ArrayList<Trip>) items;
    }
}
