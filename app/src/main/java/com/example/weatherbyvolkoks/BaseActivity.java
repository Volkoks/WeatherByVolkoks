package com.example.weatherbyvolkoks;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherbyvolkoks.data.Constants;

public class BaseActivity extends AppCompatActivity {
   private static final String NSP = "LOGIN";
   private static final String isDarkThemes = "IS_DARK_THEME";
   private BroadcastReceiver airplaneReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isDarkTheme()){
            setTheme(R.style.AppDarkTheme);
        }else {
            setTheme(R.style.AppTheme);
        }
        initChanel();
        airplaneReceiver = new AirPlaneReceiver();
        registerReceiver(airplaneReceiver, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));


    }

    private void initChanel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(Constants.CHANNEL_ID,Constants.CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
        }
    }

    protected boolean isDarkTheme(){
        SharedPreferences sP = getSharedPreferences(NSP, MODE_PRIVATE);
        return sP.getBoolean(isDarkThemes, true);
    }
    protected void setIsDarkTheme(boolean isDarkTheme){
        SharedPreferences sP = getSharedPreferences(NSP, MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();
        editor.putBoolean(isDarkThemes, isDarkTheme);
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(airplaneReceiver);
    }
}
