package com.example.tripplanner.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.POJOs.Trip;
import com.example.tripplanner.R;

import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder>  {

    private Context context;
    OnTripListener onTripListener;
    private ArrayList<Trip> items=new ArrayList<>();
    @NonNull
    @Override
    public TripAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row=null;
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row=layoutInflater.inflate(R.layout.trip_row,parent,false);
        ViewHolder holder=new ViewHolder(row,onTripListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TripAdapter.ViewHolder holder, final int position) {
        holder.date.setText(items.get(position).getTripDate());
        holder.time.setText(items.get(position).getTripTime());
        holder.name.setText(items.get(position).getTripName());
        holder.src.setText(items.get(position).getStartPoint());
        holder.dest.setText(items.get(position).getEndPoint());
        holder.type.setText(items.get(position).getStatus());

        holder.notesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(position);
                //create().show();
            }
        });
    }
    public void showDialog(int position){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Trip Notes").setIcon(R.drawable.ic_note_black_24dp);
        ArrayList<String> li=items.get(position).getNotes();
        if(li.size()==0)
            li.add("There were no notes!");
        String[] tripNotes= new String[li.size()];
        for (int i=0;i<li.size();i++){
            tripNotes[i]=li.get(i);
        }
        builder.setItems(tripNotes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView date;
        public TextView time;
        public TextView name;
        public TextView src;
        public TextView dest;
        public TextView type;
        public Button notesBtn;
        public Button menuBtn;
        public View layout;

        public void showNotes(View view) {
//            AlertDialog.Builder builder=new AlertDialog.Builder(context);
//            builder.setTitle("Notes");
//            builder.setIcon(R.drawable.ic_note_black_24dp).create().show();
        }
        OnTripListener onTripListener;
        public ViewHolder(View v,OnTripListener onTripListener){
            super(v);
            layout=v;
            this.onTripListener=onTripListener;
            v.setOnClickListener(this);
            Button noteBtn=v.findViewById(R.id.noteId);
            noteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                showNotes(v);
                }
            });
            date=v.findViewById(R.id.dateId);
            time=v.findViewById(R.id.timeId);
            name=v.findViewById(R.id.tripId);
            src=v.findViewById(R.id.srcId);
            dest=v.findViewById(R.id.destId);
            type=v.findViewById(R.id.statusId);
            notesBtn=v.findViewById(R.id.noteId);
            menuBtn=v.findViewById(R.id.menuBtnId);
        }

        @Override
        public void onClick(View v) {
            onTripListener.onTripClick(getAdapterPosition());
        }
    }
    public  interface OnTripListener{
        public void  onTripClick(int position);
    }

    public TripAdapter(@NonNull Context context, int resource, int textViewResourceId, List items,OnTripListener onTripListener) {
        this.context=context;
        this.items= (ArrayList<Trip>) items;
        this.onTripListener=onTripListener;
    }
}
