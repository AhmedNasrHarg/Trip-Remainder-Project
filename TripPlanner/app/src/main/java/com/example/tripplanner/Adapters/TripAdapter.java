package com.example.tripplanner.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.POJOs.Trip;
import com.example.tripplanner.R;
import com.example.tripplanner.Views.TripView.TripActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
        holder.notesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(position);
            }
        });

        holder.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap(position);
            }
        });
    }
    @SuppressLint("RestrictedApi")
    public void showPopup(View v) {
        // to show popup
        final PopupMenu popup = new PopupMenu(context, v);
//        popup.setOnMenuItemClickListener((TripActivity) context);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.trip_menu_layout, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(context, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
                switch (item.getItemId()) {
                    case R.id.TripNotes:
                        // can add new note
                        return true;
                    case R.id.EditTrip:
                        // can edit trip
                        return true;
                    case R.id.DeleteTrip:
                        //delete trip and add it to history as cancelled
                        return true;
                    case R.id.cancelMenu:
                        popup.dismiss();
                        return true;
                    default:
                        return false;
                }
            }
        });
        // to force showing icons
        MenuPopupHelper menuHelper = new MenuPopupHelper(context, (MenuBuilder) popup.getMenu(), v);
        menuHelper.setForceShowIcon(true);
        menuHelper.show();
    }

    public void openMap(int position)
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                                //replace address with the endPoint address
                Uri.parse("google.navigation:q="+items.get(position).getEndPoint()));
        intent.setPackage("com.google.android.apps.maps");
        context.startActivity(intent);
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
        public Button startBtn;
        public View layout;

        OnTripListener onTripListener;
        public ViewHolder(View v,OnTripListener onTripListener){
            super(v);
            layout=v;
            this.onTripListener=onTripListener;
            v.setOnClickListener(this);
            date=v.findViewById(R.id.dateId);
            time=v.findViewById(R.id.timeId);
            name=v.findViewById(R.id.tripId);
            src=v.findViewById(R.id.srcId);
            dest=v.findViewById(R.id.destId);
            type=v.findViewById(R.id.statusId);
            notesBtn=v.findViewById(R.id.noteId);
            menuBtn=v.findViewById(R.id.menuBtnId);
            startBtn=v.findViewById(R.id.startId);
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