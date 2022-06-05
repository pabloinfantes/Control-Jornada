package com.example.controljornada.ui.signup;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controljornada.R;
import com.example.controljornada.databinding.ActivitySignUpBinding;
import com.example.controljornada.ui.base.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View{

    ActivitySignUpBinding binding;
    private SignUpContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btRegistrar.setOnClickListener(view -> presenter.validateSignUp(binding.tieName.getText().toString(),
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

        String[] parts = message.split("-");
        Log.d("emailjajaj",parts.toString());
        String email = parts[0];
        String name = parts[1];

        Log.d("emailjajaj",email);
        Log.d("namejajaj",name);



        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://158.101.203.234/add/controlJornada/insertarUser.php?email="+email+"&name="+name+"&surname="+binding.tieSurname.getText().toString());
                    Log.d("url", String.valueOf(url));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.connect();

                    if( connection.getResponseCode() == HttpURLConnection.HTTP_OK ){
                        InputStream is = connection.getErrorStream();
                    }else{
                        InputStream err = connection.getErrorStream();
                    }

                    connection.disconnect();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        });
        thread.start();



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