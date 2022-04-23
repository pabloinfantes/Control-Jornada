package com.example.controljornada.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.controljornada.R;
import com.example.controljornada.databinding.ActivitySignUpBinding;
import com.example.controljornada.ui.base.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View{

    ActivitySignUpBinding binding;
    private SignUpContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btRegistrar.setOnClickListener(view -> presenter.validateSignUp(binding.tieUser.getText().toString(),
                binding.tieEmail.getText().toString(),
                binding.tiePassword.getText().toString(),
                binding.tieConfirmPassword.getText().toString()));
        presenter = new SignUpPresenter(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onSuccess(String message) {
        finish();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserEmptyError() {
        binding.tilUser.setError(getString(R.string.errEmailEmpty));
    }

    @Override
    public void setEmailEmptyError() {
        binding.tilEmail.setError(getString(R.string.errEmailEmpty));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.tilPassword.setError(getString(R.string.errEmailEmpty));
    }

    @Override
    public void setConfirmPasswordEmptyError() {
        binding.tilConfirmPassword.setError(getString(R.string.errEmailEmpty));
    }

    @Override
    public void setPasswordError() {
        binding.tilPassword.setError(getString(R.string.errorPassword));
    }

    @Override
    public void setEmailError() {
        binding.tilEmail.setError(getString(R.string.errEmail));
    }

    @Override
    public void setPasswordDontMatch() {
        Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        //Los dejo vacios pq el sign up no va a tener ninguna barra de proceso
    }

    @Override
    public void hideProgress() {
        //Los dejo vacios pq el sign up no va a tener ninguna barra de proceso

    }

    @Subscribe
    public void onEvent(Event event){
        hideProgress();
        Toast.makeText(this,event.getMessage(),Toast.LENGTH_SHORT).show();

    }
}