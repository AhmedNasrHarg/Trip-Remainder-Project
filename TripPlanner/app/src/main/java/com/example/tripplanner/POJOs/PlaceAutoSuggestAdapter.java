package com.example.tripplanner.POJOs;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class PlaceAutoSuggestAdapter extends ArrayAdapter implements Filterable {
    ArrayList<String>results;
    int resource;
    Context context;
    PlaceApi placeApi = new PlaceApi();

    public PlaceAutoSuggestAdapter(Context context, int resource) {
        super(context, resource);
        this.context= context;
        this.resource= resource;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public String getItem(int pos) {
        return results.get(pos);
    }


    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint !=null){
                    try {
                        results = placeApi.autoComplete(constraint.toString());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    filterResults.values=results;
                    filterResults.count=results.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results !=null && results.count>0){
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}
