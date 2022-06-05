package com.example.controljornada.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controljornada.ui.MainActivity;
import com.example.controljornada.R;
import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.MainActivityNormalUser;
import com.example.controljornada.ui.base.Event;
import com.example.controljornada.ui.horario.HorarioFragment;
import com.example.controljornada.ui.signup.SignUpActivity;
import com.example.controljornada.databinding.ActivityLoginBinding;
import com.example.controljornada.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private ActivityLoginBinding binding;
    private LoginContract.Presenter presenter;
    public String result;
    public static int USERID;
    public static String USEREMAIL;
    public static String ISADMIN;
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



    private void sendData(String data) {
        result =data;
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


        Thread thread = new Thread(new Runnable() {
            public String data = "";
            @Override
            public void run() {

                try {
                    String email = String.valueOf(binding.tieUser.getText());
                    URL url = new URL("http://158.101.203.234/add/controlJornada/controlJornada.php?email="+email);
                    Log.d("url", String.valueOf(url));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type", "application/json; utf-8");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.connect();

                    int code = connection.getResponseCode();
                    switch (code) {
                        case 200:
                        case 201:
                            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                            String line;
                            while ((line = br.readLine()) != null) {
                                data += line;
                            }
                            sendData(data);


                            br.close();
                    }
                    connection.disconnect();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            public String getData() {
                return data;
            }
        });
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("rr",result);

        JSONArray array = null;
        int id = 0;
        String email = null;
        String admin = null;
        String name = null;
        String surname = null;
        try {
            array = new JSONArray(result);
            for(int i=0; i < array.length(); i++)
            {
                JSONObject object = array.getJSONObject(i);
                id = object.getInt("id");
                email = object.getString("email");
                admin = object.getString("admin");
                name = object.getString("name");
                surname = object.getString("surname");

                if (admin.equals("1")){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    startActivity(new Intent(LoginActivity.this, MainActivityNormalUser.class));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putInt("id",id);
        editor.putString("admin",admin);
        editor.putString("email",email);
        editor.putString("name",name);
        editor.putString("surname",surname);
        editor.apply();


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