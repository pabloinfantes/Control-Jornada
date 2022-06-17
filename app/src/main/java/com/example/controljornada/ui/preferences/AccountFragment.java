package com.example.controljornada.ui.preferences;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.controljornada.R;
/**
 * Esta clase es la encargada de gestionar lo que ocurre en esta vista en concreto
 * @author pablo
 *
 */
public class AccountFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.account_preferences, rootKey);
        initPreferenceUser();
        intitPreferencePassword();
    }

    /**
     * Metodo que inicializa la preferencia EditText que muestra el usuario
     */
    private void initPreferenceUser() {
        EditTextPreference edUser = getPreferenceManager().findPreference(getString(R.string.key_user));
        edUser.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setSingleLine(true);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.selectAll();
            }
        });


    }


    private void intitPreferencePassword() {
        EditTextPreference edPassword = getPreferenceManager().findPreference(getString(R.string.key_password));
        edPassword.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setSingleLine(true);
                //Que el texto muestre los asteriscos en el campo EditText se debe añadir el
                //atributo InputType.TYPE_TEXT_VARIATION_PASSWORD
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                editText.selectAll();
            }
        });
    }
}
