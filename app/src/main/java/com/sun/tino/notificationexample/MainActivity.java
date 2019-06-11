package com.sun.tino.notificationexample;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.app.Notification.CATEGORY_REMINDER;
import static android.app.Notification.VISIBILITY_PUBLIC;
import static com.sun.tino.notificationexample.NotificationChanel.CHANNEL_ID;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_CP_ID = 1;
    private static final int NOTIFICATION_CR_ID = 0;
    private NotificationManagerCompat mNotificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNotificationManager = NotificationManagerCompat.from(this);
    }

    public void showNotificationCP(View view) {
        String contentTitle = getResources().getString(R.string.content_title_CP);
        String contentText = getResources().getString(R.string.content_text_CP);
        addNotification(NOTIFICATION_CP_ID, contentTitle, contentText);
    }
    public void showNotificationCR(View view) {
        String contentTitle = getResources().getString(R.string.content_title_CR);
        String contentText = getResources().getString(R.string.content_text_CR);
        addNotification(NOTIFICATION_CR_ID, contentTitle, contentText);
    }

    private void addNotification(int ID, String contentTitle, String contentText) {
        Intent intentClick = new Intent(this, MainActivity.class);
        intentClick.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingClick = PendingIntent.getActivity(this, 0, intentClick, 0);

        Intent intentAction = new Intent(this, NotificationBroadcast.class);
        intentAction.putExtra(getResources().getString(R.string.toast_message),
                contentText);
        PendingIntent pendingAction = PendingIntent.getBroadcast(this, 0, intentAction, 0);

        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setSmallIcon(R.drawable.pomodoro)
                    .setContentTitle(contentTitle)
                    .setContentText(contentText)
                    .setColor(Color.RED)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingClick)
                    .setVisibility(VISIBILITY_PUBLIC)
                    .setAutoCancel(true)
                    //.setOnlyAlertOnce(true)
                    .setCategory(CATEGORY_REMINDER)
                    .addAction(R.mipmap.ic_pomodoro, getResources().getString(R.string.notification_action), pendingAction)
                    .build();
        }
        if (notification != null) {
            mNotificationManager.notify(ID, notification);
        }
    }
}
