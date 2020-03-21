package com.example.tripplanner.Views.TripView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.tripplanner.R;

public class NotificationHelper extends ContextWrapper {
    public static final String channelName = "alarm";
    public static final String channelId = "alarm";

    NotificationManager notManager;

    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            createChannel();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelName , channelId , NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(R.color.colorPrimary);
        getManager().createNotificationChannel(channel);

    }
    public NotificationManager getManager()
    {
        if (notManager==null)
        {
            notManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notManager;
    }
    public NotificationCompat.Builder getChannelNotification(String title , String msg)
    {
        //add gradle
        return new NotificationCompat.Builder(getApplicationContext(),channelId)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.drawable.not2);
    }
}
