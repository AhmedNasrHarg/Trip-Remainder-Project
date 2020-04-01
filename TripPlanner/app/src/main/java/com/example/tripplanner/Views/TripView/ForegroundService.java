package com.example.tripplanner.Views.TripView;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.tripplanner.R;
import com.example.tripplanner.Views.HomeView.MainActivity;
public class ForegroundService extends Service {
    //MediaPlayer media;

    @Override
    public void onCreate() {
        super.onCreate();
         // media = MediaPlayer.create(this, R.raw.cool);
       //  media.setLooping(true); // Set looping
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
   //         media.start();
        String reqCode=intent.getStringExtra("reqCode");
        Intent notifiIntent = new Intent(this, Dialog.class);

         PendingIntent pending =  PendingIntent.getActivity(this, 1, notifiIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_ID)
                .setContentTitle("Notification")
                .setContentText("Take care you'll miss your trip")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pending)
                .build();
        startForeground(1, notification);

        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
       //   media.stop();
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
