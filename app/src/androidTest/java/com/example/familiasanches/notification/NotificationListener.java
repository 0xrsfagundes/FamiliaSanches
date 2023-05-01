package com.example.familiasanches.notification;

import android.graphics.Bitmap;
import android.os.Environment;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class NotificationListener extends NotificationListenerService {
    private static final String TAG = "NotificationListener";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        // Obtém a notificação
        android.app.Notification notification = sbn.getNotification();
        // Obtém o ícone da notificação
        Bitmap icon = notification.largeIcon;

        // Salva o ícone em um arquivo
        try {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            String fileName = "notification_" + new Date().getTime() + ".png";
            File file = new File(path, fileName);
            FileOutputStream out = new FileOutputStream(file);
            icon.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            Log.i(TAG, "Ícone salvo em " + file.getAbsolutePath());
        } catch (IOException e) {
            Log.e(TAG, "Erro ao salvar o ícone da notificação", e);
        }
    }
}
