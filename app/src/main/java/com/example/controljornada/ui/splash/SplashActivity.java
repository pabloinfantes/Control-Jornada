package com.example.controljornada.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.controljornada.R;
import com.example.controljornada.databinding.ActivitySplashBinding;
import com.example.controljornada.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;

    //Declarar variable constante privada
    private static final long WAIT_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(() -> starLogin(),WAIT_TIME);
    }

    private void starLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        //Voy a llamar de forma explicita al metodo finish() de una activity, para eliminar
        //esta activity de la pila de actividades, porque si el usuario pulsa back no que se visualice de nuevo.
        finish();
    }
}