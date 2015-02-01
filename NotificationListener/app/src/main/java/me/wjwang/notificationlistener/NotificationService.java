package me.wjwang.notificationlistener;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.LocalBroadcastManager;

public class NotificationService extends NotificationListenerService {

    public static final String MSG = "Service started";
    public static String syncName = "NotificationService";

    public void onNotificationPosted(StatusBarNotification sbn) {
        Bundle extras = sbn.getNotification().extras;
        String notificationTitle = extras.getString(Notification.EXTRA_TITLE);
        CharSequence notificationText = extras.getCharSequence(Notification.EXTRA_TEXT);
        String message = "onNotificationPosted" + "|" + sbn.getPackageName() + "|"
                + sbn.getPostTime() + "|" + notificationTitle + "|" + notificationText;
        sendMessage(message);
    }

    public void onNotificationRemoved(StatusBarNotification sbn) {
        Bundle extras = sbn.getNotification().extras;
        String notificationTitle = extras.getString(Notification.EXTRA_TITLE);
        CharSequence notificationText = extras.getCharSequence(Notification.EXTRA_TEXT);
        String message = "onNotificationRemoved" + "|" + sbn.getPackageName() + "|"
                + sbn.getPostTime() + "|" + notificationTitle + "|" + notificationText;
        sendMessage(message);
    }

    private void sendMessage(String message) {
        Intent notifyIntent = new Intent(syncName);
        notifyIntent.putExtra("message", message);
        LocalBroadcastManager.getInstance(this).sendBroadcast(notifyIntent);
    }
}