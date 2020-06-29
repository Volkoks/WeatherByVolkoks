package com.example.weatherbyvolkoks;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherbyvolkoks.data.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class BaseActivity extends AppCompatActivity {
    private static final String NSP = "LOGIN";
    private static final String isDarkThemes = "IS_DARK_THEME";
    private BroadcastReceiver airplaneReceiver;
    private BroadcastReceiver batteryLevel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isDarkTheme()) {
            setTheme(R.style.AppDarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        initChanel(Constants.CHANNEL_ID_AIRPLANE_MODE, Constants.CHANNEL_NAME);
        initChanel(Constants.CHANNEL_ID_BATTERY, Constants.CHANNEL_NAME_BATTERY);
        initChanel(Constants.CHANNEL_ID_STORM_WARNING_MESSAGE, Constants.CHANNEL_NAME);

        batteryLevel = new BatteryLevelReceiver();
        airplaneReceiver = new AirPlaneReceiver();

        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction(Intent.ACTION_BATTERY_LOW);

        registerReceiver(airplaneReceiver, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
        registerReceiver(batteryLevel, ifilter);

        initToken();

    }

    private void initToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            // Не удалось получить токен, произошла ошибка
                            Log.w("TAG", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Получить токен
                        String token = task.getResult().getToken();
                        Log.w("[TOKEN]", token);
                    }
                });
    }

    private void initChanel(String ID, String name) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }


    protected boolean isDarkTheme() {
        SharedPreferences sP = getSharedPreferences(NSP, MODE_PRIVATE);
        return sP.getBoolean(isDarkThemes, true);
    }

    protected void setIsDarkTheme(boolean isDarkTheme) {
        SharedPreferences sP = getSharedPreferences(NSP, MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();
        editor.putBoolean(isDarkThemes, isDarkTheme);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(airplaneReceiver);
        unregisterReceiver(batteryLevel);
    }
}
