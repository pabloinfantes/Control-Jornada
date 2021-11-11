package com.example.controljornada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.controljornada.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.fragment_aboutus_menu,menu);
//        return super.onCreateOptionsMenu(menu);
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_aboutus:
//                Toast.makeText(this,"Se ha pulsado Sobre Nosotros",Toast.LENGTH_SHORT).show();
//                return true;
//            case  R.id.action_settings:
//                Toast.makeText(this,"Se ha pulsado Ajustes",Toast.LENGTH_SHORT).show();
//                return true;
//                default:
//                //Si los fragment modifican el menu de la activity se devuelve false
//                return false;
//        }
//
//    }
}