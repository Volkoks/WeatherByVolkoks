package com.example.weatherbyvolkoks;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.weatherbyvolkoks.data.Constants;

public class BatteryLevelReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
     if(intent.getAction().equals(Intent.ACTION_BATTERY_LOW)){
initNotification(context,"НИЗКИЙ ЗАРЯД", "Низкий заряд баттареи! Осуществите зарядку!");
     }
    }
    private void initNotification(Context context, String title, String text){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Constants.CHANNEL_ID_BATTERY)
                .setSmallIcon(R.drawable.ic_battery_warning)
                .setContentTitle(title)
                .setContentText(text);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(Constants.ID_CHANNEL_BATTERY,builder.build());

    }
}
