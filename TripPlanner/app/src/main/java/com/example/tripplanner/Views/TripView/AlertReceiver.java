package com.example.tripplanner.Views.TripView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationHelper nHelp = new NotificationHelper(context);
        NotificationCompat.Builder nb = nHelp.getChannelNotification( "Alarm" , "Don't miss your trip");
        nHelp.getManager().notify(1,nb.build());
    }
}
