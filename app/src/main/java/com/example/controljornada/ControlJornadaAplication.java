package com.example.controljornada;

import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.example.controljornada.data.ControlJornadaDatabase;
import com.example.controljornada.ui.horario.NotificationReceiver;

import java.util.Calendar;

public class ControlJornadaAplication extends Application {


    public static final String IDCHANEL = "243424211";


    @Override
    public void onCreate() {
        super.onCreate();
        ControlJornadaDatabase.create(this);
        createNotificationChannel();

        NotificationReceiver notification = new NotificationReceiver();
        IntentFilter intentFilter = new IntentFilter("com.example.controljornada");
        registerReceiver(notification,intentFilter);

    }



    /**
     * Este metodo crea el canal que se utilizara en las notificaciones de la aplicacion
     */
    private void createNotificationChannel() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            String nameChannel = getString(R.string.name_channel);
            NotificationChannel notificationChannel = new NotificationChannel(IDCHANEL,nameChannel,importancia);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
        }
    }

}
