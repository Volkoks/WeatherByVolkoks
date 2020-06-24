package com.example.weatherbyvolkoks;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.weatherbyvolkoks.data.Constants;

public class AirPlaneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Constants.CHANNEL_ID_AIRPLANE_MODE)
                .setSmallIcon(R.drawable.ic_airplane_mode)
                .setContentTitle("WeatherByVolkoks")
                .setContentText("Включен режим самолёта!");
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(Constants.ID_CHANNEL_AIRPLANE_MODE,builder.build());
    }
}
