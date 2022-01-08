package com.example.controljornada.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controljornada.MainActivity;
import com.example.controljornada.R;
import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.Event;
import com.example.controljornada.ui.signup.SignUpActivity;
import com.example.controljornada.databinding.ActivityLoginBinding;
import com.example.controljornada.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private ActivityLoginBinding binding;
    private LoginContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btSignUp.setOnClickListener(view -> startActivityLogin());
        binding.btSignIn.setOnClickListener(view -> presenter.validateCredentials(new User(binding.tieUser.getText().toString(),binding.tiePassword.getText().toString())));

        binding.tieUser.addTextChangedListener(new LoginTextWatcher(binding.tieUser));
        binding.tiePassword.addTextChangedListener(new LoginTextWatcher(binding.tiePassword));
        presenter = new LoginPresenter(this);

        //la vista se registra como subscriptor del EventBus
        EventBus.getDefault().register(this);
    }


    private void startActivityLogin() {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    private void startMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showProgress() {
        binding.progressHorizontal.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.progressHorizontal.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSuccess(String message) {
        startMainActivity();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }



    @Override
    public void setEmailEmptyError() {
        binding.tilUser.setError(getString(R.string.errEmailEmpty));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.tilPassword.setError(getString(R.string.errorPasswordEmpty));
    }

    @Override
    public void setPasswordError() {
        binding.tilPassword.setError(getString(R.string.errorPassword));
    }





    class LoginTextWatcher implements TextWatcher {
        private View view;

        private LoginTextWatcher(View view){
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
                case R.id.tiePassword:
                    validatePassword(((EditText)view).getText().toString());
                    break;
            }
        }


    }

    private void validatePassword(String password) {

        if (TextUtils.isEmpty(password)){
            binding.tilPassword.setError(getString(R.string.errorPasswordEmpty));
        } else if (!CommonUtils.isPasswordValid(password)){
            binding.tilPassword.setError(getString(R.string.errorPassword));
        } else{
            //desaparece el error
            binding.tilPassword.setError(null);
        }
    }


    private void validateEmail(String email) {
        if (TextUtils.isEmpty(email)){
            binding.tilUser.setError(getString(R.string.errEmailEmpty));
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilUser.setError(getString(R.string.errEmail));
        } else{
            //desaparece el error
            binding.tilUser.setError(null);
        }
    }
    @Subscribe
    public void onEvent(Event event){
        hideProgress();
        Toast.makeText(this,event.getMessage(),Toast.LENGTH_SHORT).show();
    }
}