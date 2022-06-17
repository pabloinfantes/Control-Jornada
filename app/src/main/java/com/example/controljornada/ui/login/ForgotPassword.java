package com.example.controljornada.ui.login;

import android.content.Intent;
import android.os.Bundle;

import com.example.controljornada.databinding.ActivityForgotPasswordBinding;
import com.example.controljornada.databinding.ActivityLoginBinding;
import com.example.controljornada.ui.base.Event;
import com.example.controljornada.utils.CommonUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.controljornada.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
/**
 * Esta clase es la encargada de gestionar lo que ocurre en esta vista en concreto
 * @author pablo
 *
 */
public class ForgotPassword extends AppCompatActivity {


    private ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tieEmail.addTextChangedListener(new RecuperarTextWatcher(binding.tieEmail));
        //la vista se registra como subscriptor del EventBus
        EventBus.getDefault().register(this);

        binding.btRecu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(binding.tieEmail.getText().toString());
            }
        });

    }

    private void sendEmail(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ForgotPassword.this,"Correo enviado",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPassword.this,LoginActivity.class));
                            finish();
                        }else {
                            Toast.makeText(ForgotPassword.this,"correo invalido",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ForgotPassword.this,LoginActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    class RecuperarTextWatcher implements TextWatcher {
        private View view;

        private RecuperarTextWatcher(View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            //se deja vacio
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //se deja vacio
        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){
                case R.id.tieEmail:
                    validateEmail(((EditText)view).getText().toString());
                    break;

            }
        }


    }


    private void validateEmail(String email) {

         if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tieEmail.setError(getString(R.string.errEmail));
        } else{
            //desaparece el error
            binding.tieEmail.setError(null);
        }
    }
    @Subscribe
    public void onEvent(Event event){
        Toast.makeText(this,event.getMessage(),Toast.LENGTH_SHORT).show();
    }


}