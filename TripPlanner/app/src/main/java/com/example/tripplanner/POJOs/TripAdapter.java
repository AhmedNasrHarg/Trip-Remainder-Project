package com.example.tripplanner.POJOs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
//        row=layoutInflater.inflate(R.layout.message_row,parent,false);
        ViewHolder holder=new ViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TripAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
//        public TextView title;
//        public TextView body;
        public View layout;

        public ViewHolder(View v){
            super(v);
            layout=v;
//            title=v.findViewById(id.row_title);   // take care ids from custom row
//            body=v.findViewById(id.row_body);
        }

    }

    public TripAdapter(@NonNull Context context, int resource, int textViewResourceId, List items) {
        this.context=context;
        this.items= (ArrayList<Trip>) items;
    }
}
