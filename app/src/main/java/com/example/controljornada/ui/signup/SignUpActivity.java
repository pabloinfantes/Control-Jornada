package com.example.controljornada.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.controljornada.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btRegistrar.setOnClickListener(view -> onBackPressed());
    }
}