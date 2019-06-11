package com.sun.tino.notificationexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(context.getString(R.string.toast_message));
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
