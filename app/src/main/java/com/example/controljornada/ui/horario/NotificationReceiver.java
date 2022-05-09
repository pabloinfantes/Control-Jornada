package com.example.controljornada.ui.horario;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


import androidx.annotation.RequiresApi;

import com.example.controljornada.ControlJornadaAplication;
import com.example.controljornada.R;
import com.example.controljornada.ui.MainActivity;

import java.util.Random;

public class NotificationReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotification("Es necesario firmar antes de trabajar",context);
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotification(String message, Context context) {

        Notification.Builder builder = new Notification.Builder(context, ControlJornadaAplication.IDCHANEL)
                .setSmallIcon(R.drawable.ic_action_email)
                .setAutoCancel(true)
                .setContentTitle("Recordatorio de firma")
                .setContentText(message);

        //6.- Añadir notificacion al manager
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(), builder.build());

    }
}
