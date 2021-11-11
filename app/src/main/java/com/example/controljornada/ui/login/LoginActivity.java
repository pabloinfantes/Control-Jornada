package com.example.controljornada.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.controljornada.MainActivity;
import com.example.controljornada.ui.signup.SignUpActivity;
import com.example.controljornada.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btSignIn.setOnClickListener(view -> startMainActivity());
        binding.btSignUp.setOnClickListener(view -> startActivityLogin());
    }

    private void startActivityLogin() {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    private void startMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}