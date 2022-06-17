package com.example.controljornada.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.controljornada.R;
import com.example.controljornada.databinding.ActivityMainNormalUserBinding;

import java.util.HashSet;
import java.util.Set;
/**
 * Esta clase es la encargada de gestionar lo que ocurre en esta vista en concreto
 * @author pablo
 *
 */
public class MainActivityNormalUser extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback{


    private ActivityMainNormalUserBinding binding;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private static int REQUESTCODE = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainNormalUserBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.include.toolbar);
        setContentView(binding.getRoot());

        //Inicializar el controlador de navegacion en la aplicacion
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);



        Set<Integer> topLevelDestination = new HashSet<>();
        topLevelDestination.add(R.id.aboutUsFragment);
        topLevelDestination.add(R.id.settingsFragment);
        topLevelDestination.add(R.id.horarioContenedorFragment);

        NavigationUI.setupWithNavController(binding.navigationview1,navController);
        appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestination)
                .setOpenableLayout(binding.drawerlayout1)
                .build();

        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString("name", "8:01");
        String email = prefs.getString("email", "8:01");

        View headerView = binding.navigationview1.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.tvUserNav1);
        TextView navUserEmail = (TextView) headerView.findViewById(R.id.tvEmailNav1);
        navUsername.setText(name);
        navUserEmail.setText(email);


        /*
        String hora = prefs.getString(getString(R.string.key_hora), "8:01");
        Log.d("Hora",hora);
        String horareal = hora.substring(0, 1);
        String min = hora.substring(2, 4);

        Log.d("Hora",horareal);
        Log.d("Hora",min);

        Intent intent = new Intent("com.example.controljornada");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUESTCODE,intent,PendingIntent.FLAG_UPDATE_CURRENT);


        // En la alarma el tiempo seleccionado por el usuario en las preferencias
        Calendar event = Calendar.getInstance();
        event.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horareal));
        event.set(Calendar.MINUTE, Integer.parseInt(min));
        event.set(Calendar.SECOND, 0);

//
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, event.getTimeInMillis(), pendingIntent);

         */

    }






    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerlayout1.isDrawerOpen(GravityCompat.START))
            binding.drawerlayout1.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }


    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        if (pref.getKey().equals(getString(R.string.key_account))){
            navController.navigate(R.id.action_settingsFragment_to_accountFragment);
        }
        return true;
    }
}