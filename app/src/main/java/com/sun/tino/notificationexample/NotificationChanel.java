package com.sun.tino.notificationexample;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class NotificationChanel extends Application {
    public static final String CHANNEL_ID = "tino.chanel.id";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChanel();
    }

    private void createNotificationChanel(){
        CharSequence name = this.getString(R.string.chanel_name);
        String description = this.getString(R.string.channel_description);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
