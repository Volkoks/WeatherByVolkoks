package com.example.weatherbyvolkoks;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.weatherbyvolkoks.data.Constants;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class StormWarningMessage extends FirebaseMessagingService {
    public StormWarningMessage() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title = remoteMessage.getNotification().getTitle();
        if (title == null) {
            title = "ШТОРМОВОЕ ПРЕДУПРЕЖДЕНИЕ!(сток)";
        }
        String text = remoteMessage.getNotification().getBody();
        if (text == null) {
            text = "Текст не пришел!";
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Constants.CHANNEL_ID_STORM_WARNING_MESSAGE)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_warning);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(Constants.ID_CHANNEL_STORM_WARNING_MESSAGE, builder.build());
    }

    @Override
    public void onNewToken(String token) {
        Log.d("[TOKEN]",  token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);

    }

    private void sendRegistrationToServer(String token) {

    }
}
