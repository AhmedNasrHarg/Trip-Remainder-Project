package com.example.tripplanner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.POJOs.Trip;
import com.example.tripplanner.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>  {

    private Context context;
    HistoryAdapter.OnHistoryListener onHistoryListener;
    private ArrayList<Trip> items=new ArrayList<>();
    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row=null;
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row=layoutInflater.inflate(R.layout.history_row,parent,false);
        ViewHolder holder=new ViewHolder(row,onHistoryListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        holder.date.setText(items.get(position).getTripDate());
        holder.time.setText(items.get(position).getTripTime());
        holder.name.setText(items.get(position).getTripName());
        holder.src.setText(items.get(position).getStartPoint());
        holder.dest.setText(items.get(position).getEndPoint());
        holder.type.setText(items.get(position).getStatus());
        // add buttons to holder class by findViewById(), then handle events here;
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
        public View layout;

        public void showNotes(View view) {
//            AlertDialog.Builder builder=new AlertDialog.Builder(context);
//            builder.setTitle("Notes");
//            builder.setIcon(R.drawable.ic_note_black_24dp).create().show();
        }
        OnHistoryListener onHistoryListener;
        public ViewHolder(View v,OnHistoryListener onHistoryListener){
            super(v);
            layout=v;
            this.onHistoryListener=onHistoryListener;
            v.setOnClickListener(this);
            Button noteBtn=v.findViewById(R.id.noteIdHist);
            noteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNotes(v);
                }
            });
            date=v.findViewById(R.id.dateIdHist);
            time=v.findViewById(R.id.timeIdHist);
            name=v.findViewById(R.id.tripIdHist);
            src=v.findViewById(R.id.srcIdHist);
            dest=v.findViewById(R.id.destIdHist);
            type=v.findViewById(R.id.statusIdHist);
        }

        @Override
        public void onClick(View v) {
            onHistoryListener.onHistoryClick(getAdapterPosition());
        }
    }
    public  interface OnHistoryListener{
        public void  onHistoryClick(int position);
    }

    public HistoryAdapter(@NonNull Context context, int resource, int textViewResourceId, List items, OnHistoryListener onHistoryListener) {
        this.context=context;
        this.items= (ArrayList<Trip>) items;
        this.onHistoryListener=onHistoryListener;
    }
}
